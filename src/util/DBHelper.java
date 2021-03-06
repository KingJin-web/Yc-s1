package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;


/**
 * 结果集封装
 * 实体类 	==> 	表
 * 一个对象    	==>  	一条记录
 * 属性		==>		字段
 * 映射
 * 组合条件查询
 * 基于sql语句的动态的拼接条件
 * 1. 	where 1=1 ==> 无用的查询条件, 方便拼接sql语句
 * 2. 	使用List集合保存查询参数
 * <p>
 * 日期类型
 * Date  ==>  年月日	==> java.sql.Date	---> 父类 java.util.Date
 * datetime   年月日时分秒  ==> java.sql.Timestamp --> 父类 java.util.Date
 * timestamp
 * <p>
 * java.sql.Date.valueOf("yyyy-MM-dd"); "20203-3-25";
 * <p>
 * 分页查询
 * <p>
 * 事务管理
 * <p>
 * DBHelper 封装 : 简化 jdbc 的代码
 * 1.加载驱动	只加载一次 ==> 静态
 * 2.获取连接
 * 3.创建语句
 * 4.执行语句
 * 5.关闭连接
 * <p>
 * 代码冗余 ==> 重复   ==> 提炼 ==> 代码精简
 * <p>
 * DBHelper 用于执行 sql 语句 返回对应的结果
 * 1. 增删改  ==>  executeUpdate  ==> 返回: 数字  int , 增删改的行数
 * 2. 查询     ==>  executeQuery	 ==> 返回:ResultSet,
 * 因为需要关闭, 应该返回实体类, 或者Map集合
 */
public class DBHelper {

    // 定义共用的连接对象
    private Connection conn;

    private boolean isAutoCommit = true;


    private static String driver;
    private static String url;
    private static String name;
    private static String pwd;

    /**
     * 静态块，初始化
     */
    static {
        try {
            // 读取配置文件 ==> 通过类加载器读取类路径里面的文件
            String path = "conn.properties";
            InputStream in = DBHelper.class.getClassLoader().getResourceAsStream(path);
            // 创建集合对象
            Properties prop = new Properties();
            prop.load(in);
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            name = prop.getProperty("name");
            pwd = prop.getProperty("pwd");
            Class.forName(driver);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 isAutoCommit 决定是否自动提交
     * <p>
     * 如果是自动提交, 则意味着每次执行 update 方法都要获取新的连接, 在执行之后关闭连接
     * 否则, 不关闭连接
     *
     * @param isAutoCommit 自动提交  true
     */
    public DBHelper(boolean isAutoCommit) {
        this.isAutoCommit = isAutoCommit;
        if (!isAutoCommit) {
            try {
                conn = getConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * JDBC 连接默认是自动提交, 也就是每次执行完增删改都会自动提交
     * 无参的构造方法, 可以注释掉了
     */
    public DBHelper() {

    }

    // 关闭连接
    public void closeConnection() {
        IOHelper.close(conn);
    }

    // 返回连接对象
    public Connection getConn() {
        return conn;
    }

    /**
     * 获取连接对象
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, name, pwd);
    }
//    
//    /**
//     * 获取连接
//     *
//     * @return
//     */
//    public Connection getConnection() {
//
////        String DB_URL = "jdbc:mysql://kingmysql.mysql.rds.aliyuncs.com:3306/ycs1";
////        // 数据库的用户名与密码，需要根据自己的设置
////        String USER = "ycs1";
////        String PASS = "aaaaaaaa";
//
//        String DB_URL = "jdbc:mysql://localhost:3306/ycs1?serverTimezone=GMT%2B8";
//        String USER = "root";
//        String PASS = "aaaa";
//        try {
//            if (isAutoCommit) {
//                return DriverManager.getConnection(DB_URL, USER, PASS);
//            } else {
//                if (conn == null) {
//                    // 禁止自动提交
//                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                    conn.setAutoCommit(isAutoCommit);
//                }
//                return conn;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("获取数据库连接失败!", e);
//        }
//    }

    /**
     * 执行修改数据库的语句
     * sql = "update emp set ename = ? where empno=?"
     * update(sql,2,3,)
     *
     * @param sql    执行的sql语句
     * @param params 可变参数数组
     * @return
     */
    public int update(String sql, Object... params) {
        try {
            // 每次都会通过open方法获取连接
            conn = getConnection();
            System.out.println("SQL: " + sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            // alrt + /
            System.out.println("参数: " + Arrays.toString(params));
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("执行SQL语句失败!", e);
        } finally {
            if (isAutoCommit) {
                IOHelper.close(conn);
            }
        }
    }


    /**
     * 执行查询语句
     *
     * @param sql
     * @param params
     * @return
     */
    public List<Map<String, Object>> query(String sql, Object... params) {
        try {
            conn = getConnection();
            System.out.println("SQL: " + sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            // alrt + /
            System.out.println("参数: " + Arrays.toString(params));
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ResultSet rs = ps.executeQuery();

            // 获取结果集元数据对象, 元(Meta)数据(data): 描述数据的数据
            ResultSetMetaData rsmd = rs.getMetaData();
            // 创建返回结果对象
            List<Map<String, Object>> ret = new ArrayList<>();
            while (rs.next()) {
                // 创建 map 集合
                Map<String, Object> row = new LinkedHashMap<>();
                // 获取每一个字段值, 设置到一个map中
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    String columnName = rsmd.getColumnName(i + 1);
                    Object columnValue = rs.getObject(columnName);
                    row.put(columnName, columnValue);
                }
                // 将 map 添加到 ret 中
                ret.add(row);
            }
            return ret;
        } catch (SQLException e) {
            throw new RuntimeException("执行SQL语句失败!", e);
        } finally {
            if (isAutoCommit) {
                IOHelper.close(conn);
            }
        }
    }

    // 将图片插入数据库
    public void readImage2DB() {
        String path = "D:/1.png";
        PreparedStatement ps = null;
        FileInputStream in = null;
        try {
            in = ImageUtil.readImage(path);
            conn = getConnection();
            String sql = "insert into photo (id,name,photo)values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setString(2, "Tom");
            ps.setBinaryStream(3, in, in.available());
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println("插入成功！");
            } else {
                System.out.println("插入失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOHelper.close(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        String path = "C:\\Users\\King\\Pictures\\psc.png";
        FileInputStream in = null;
        try {
            in = ImageUtil.readImage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sql = "UPDATE student set photo = ? where sname = ?";
        DBHelper dbHelper = new DBHelper();

        dbHelper.update(sql, in, "陈栋");

    }

    // 读取数据库中图片 输出到指定路径中
    public void readDB2Image(String sql,String targetPath, String sname) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream in = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, sname);
            rs = ps.executeQuery();
            while (rs.next()) {
                String imgFile = rs.getString("imgFile");
                in = rs.getBinaryStream("photo");
                ImageUtil.readBin2Image(in, targetPath + "\\" + imgFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOHelper.close(conn, rs, ps);
            IOHelper.close(in);
        }
    }

    /**
     * 返回值的类型是可变的类型, 所有的集合==> 泛型类
     * query 方法改造成 泛型方法	: 语法的定义: 在方法前用 <E>
     *
     * @param sql
     * @param cls    类对象, 表示 E 类的类对象, Java 反射技术
     * @param params
     * @return
     */
    public <E> List<E> query(String sql, Class<E> cls, Object... params) {
        try {
            conn = getConnection();
            System.out.println("SQL: " + sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            // alrt + /
            System.out.println("参数: " + Arrays.toString(params));
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ResultSet rs = ps.executeQuery();

            // 获取结果集元数据对象, 元(Meta)数据(data): 描述数据的数据
            ResultSetMetaData rsmd = rs.getMetaData();
            // 创建返回结果对象
            List<E> ret = new ArrayList<>();
            while (rs.next()) {
                // 创建 实体对象集合( 通过反射机制创建实体对象  == new 实体类()   )
                E e;
                try {
                    e = cls.newInstance();
                } catch (Exception e2) {
                    // 异常转型
                    throw new RuntimeException(e2);
                }

                // 通过反射进行属性值的设置
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    try {
                        // 根据当前的列名找对应的属性
                        String columnName = rsmd.getColumnName(i + 1);
                        // ID, NAME, AUTHER ...
                        columnName = columnName.toLowerCase();
                        // 转小写
                        // 获取该类定义的属性(包括私有)
                        Field field = cls.getDeclaredField(columnName);
                        Object destValue = null;
                        // 一定要判断非空, 否则会导致类型转换错误
//						if(value==null) {
//							continue;
//						}
                        if (field.getType().equals(Long.class)) {
                            destValue = rs.getLong(i + 1);

                        } else if (field.getType().equals(Integer.class)) {
                            destValue = rs.getInt(i + 1);

                        } else if (field.getType().equals(Double.class)) {
                            destValue = rs.getDouble(i + 1);

                        } else if (field.getType().equals(Byte.class)) {
                            destValue = rs.getByte(i + 1);

                        } else if (field.getType().equals(Boolean.class)) {
                            destValue = rs.getBoolean(i + 1);

                        } else if (field.getType().equals(Timestamp.class)) {
                            destValue = rs.getTimestamp(i + 1);

                        } else if (field.getType().equals(InputStream.class)) {
                            destValue = rs.getBinaryStream(i + 1);
                            // 其他数据类型请自行添加
                        } else {
                            destValue = rs.getObject(i + 1);
                        }
                        // 设置强制访问私有属性
                        field.setAccessible(true);
                        // 将值设置到该属性中
                        field.set(e, destValue);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                // 将 map 添加到 ret 中
                ret.add(e);
            }
            return ret;
        } catch (SQLException e) {
            throw new RuntimeException("执行SQL语句失败!", e);
        } finally {
            if (isAutoCommit) {
                IOHelper.close(conn);
            }
        }
    }

    public List<Map<String, Object>> query1(String sql, Class<Map<String, Object>> cls, Object... params) {
        try {
            conn = getConnection();
            System.out.println("SQL: " + sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            // alrt + /
            System.out.println("参数: " + Arrays.toString(params));
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ResultSet rs = ps.executeQuery();
            // 获取结果集元数据对象, 元(Meta)数据(data): 描述数据的数据
            ResultSetMetaData rsmd = rs.getMetaData();
            // 创建返回结果对象
            List<Map<String, Object>> ret = new ArrayList<>();
            while (rs.next()) {
                // 创建 实体对象集合( 通过反射机制创建实体对象  == new 实体类()   )
                Map<String, Object> e;
                try {
                    e = cls.newInstance();
                } catch (Exception e2) {
                    // 异常转型
                    throw new RuntimeException(e2);
                }

                // 通过反射进行属性值的设置
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    try {
                        // 根据当前的列名找对应的属性
                        String columnName = rsmd.getColumnName(i + 1); // ID, NAME, AUTHER ...
                        columnName = columnName.toLowerCase(); // 转小写
                        // 获取该类定义的属性(包括私有)
                        Field field = cls.getDeclaredField(columnName);
                        // 获取当前列的值
                        /**
                         *  ID ==> JDBC 数据类型 : BigDecimal 大实数 表示任意大小的数字
                         *  	  	    实体类类型: Long
                         *  .getType 获取属性的类型  ==> LONG  String  Integer
                         */
                        // 从结果取出的数值
                        //Object value = rs.getObject(i+1);
                        // 要转换的数值
                        Object destValue = null;
                        // 一定要判断非空, 否则会导致类型转换错误
//						if(value==null) {
//							continue;
//						}
                        if (field.getType().equals(Long.class)) {
                            destValue = rs.getLong(i + 1);
                            //destValue = Long.valueOf(value + "");
                        } else if (field.getType().equals(Integer.class)) {
                            destValue = rs.getInt(i + 1);
                            //destValue = Integer.valueOf(value + "");
                        } else if (field.getType().equals(Double.class)) {
                            destValue = rs.getDouble(i + 1);
                            //destValue = Double.valueOf(value + "");
                        } else if (field.getType().equals(Byte.class)) {
                            destValue = rs.getByte(i + 1);
                            //destValue = Byte.valueOf(value + "");
                        } else if (field.getType().equals(Boolean.class)) {
                            destValue = rs.getBoolean(i + 1);
                            //destValue = Boolean.valueOf(value + "");
                        } else if (field.getType().equals(Timestamp.class)) {
                            destValue = rs.getTimestamp(i + 1);
                            //destValue = Byte.valueOf(value + "");

                            // 其他数据类型请自行添加
                        } else {
                            destValue = rs.getObject(i + 1);
                        }
                        // 设置强制访问私有属性
                        field.setAccessible(true);
                        // 将值设置到该属性中
                        field.set(e, destValue);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                // 将 map 添加到 ret 中
                ret.add(e);
            }
            return ret;
        } catch (SQLException e) {
            throw new RuntimeException("执行SQL语句失败!", e);
        } finally {
            if (isAutoCommit) {
                IOHelper.close(conn);
            }
        }
    }


    /**
     * 分页查询: 翻书
     *
     * @param sql
     * @param pageNumber 第几页	=>  1,2,3 大于0
     * @param pageSize   每页行数	=>  3,4 大于0
     * @param params     可变参数数组: 只能放在最后一个参数位置
     * @return
     */
    public List<Map<String, Object>> queryPage(String sql, int pageNumber, int pageSize, Object... params) {
        // 参数顺序: 原参数数组 , 截止行数, 开始行数

        // number = 1, size = 5; => begin = 1, end =5
        // number = 2, size = 5; => begin = 6, end = 10
        // number = 2, size = 5; => begin = 11, end = 15

        int begin = (pageNumber - 1) * pageSize + 1;
        int end = pageNumber * pageSize;
        // 创建新参数数组
        Object[] newParams = new Object[params.length + 2];

        // 数组的复制: 1,引用复制, 2克隆复制 , 3 system.arraycopy
        System.arraycopy(params, 0, newParams, 0, params.length);

        // 给新的参数数组最后2个元素赋值
        newParams[newParams.length - 2] = end;
        newParams[newParams.length - 1] = begin;

        sql = "select *\n" + "  from (select a.*, rownum rn\n" + "          from (" + sql + ") a\n"
                + "         where rownum <= ?)\n" + " where rn >= ? ";

        // 调用 query 查询方法
        return query(sql, newParams);
    }

    /**
     * 作业: 请返回该语句结果集的行数
     *
     * @param sql
     * @param params
     * @return
     */
    public int count(String sql, Object... params) {
        // select * from emp where ename like '%A%'
        // return query(sql, params).size();
        // 子查询 => select count(*) cnt from (select * from emp where ename like
        // '%A%') ;
        sql = "select count(*) cnt from (" + sql + ")";
        Object cnt = query(sql, params).get(0).get("CNT");
        // Object ==> int   强制类型转换 ==> 类型匹配    String =>  int
        // int ret = (int) cnt; // 注定失败 cnt 类型是未知 ??   Integer Long BigDecimal 大实数
        int ret = Integer.valueOf("" + cnt);

        return ret;
    }

    /**
     * 作业: 返回结果集中, 第一行,第一列的值
     * 例如: select count(*) from emp;
     *
     * @return
     */
    public Object getValue(String sql, Object... params) {
        List<Map<String, Object>> list = query(sql, params);
        Map<String, Object> row = list.get(0);
        for (Entry<String, Object> entry : row.entrySet()) {
            return entry.getValue();
        }
        return null;
    }

    /**
     * 提交数据
     */
    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚数据
     */
    public void rollback() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//	public List<Map<String, Object>> query1(String sql, Class<Lend> class1, Object[] array) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
