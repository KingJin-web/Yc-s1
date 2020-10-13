package util;

import biz.BizException;

import java.io.*;

/**
 * IO工具类
 */
public class IOHelper {

    /**
     * 关闭流的工具方法,  所有的流都实现了 Closeable 方法, 所以都有close 方法, 也就是说:
     * Closeable 是所有流的父类,  这里使用的就是OOP多态性
     *
     * @param c
     */
    public static void close(AutoCloseable c) {
        if (c != null) {
            /**
             * 	关于如何打开错误解决窗口
             * 	1, 鼠标停在 报错 点上, org.eclipse 会给出解决方案, 其中就包括 try
             * 	2, 光标停在 报错 点上  ctrl + 1
             */
            try {
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Closeable... c) {
        for (Closeable closeable : c) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(AutoCloseable... c) {
        for (AutoCloseable autoCloseable : c) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void MoveFile(String oldFile, String newFile) {
        try {
            File file = new File(oldFile); //源文件
            if (file.renameTo(new File(newFile + file.getName()))) //源文件移动至目标文件目录
            {
                System.out.println("File is moved successful!");//输出移动成功
            } else {
                System.out.println("File is failed to move !");//输出移动失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void MoveFile2(String from, String to) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(from));
        BufferedWriter out = new BufferedWriter(new FileWriter(to));
        String line = null;
        int linenumber = 0;
        while ((line = in.readLine()) != null) {
            out.write(line + "\n");
            linenumber++;
        }
        in.close();
        out.close();

        System.out.println("line number " + linenumber);
    }

    /**
     * 实现文件的复制粘贴
     *
     * @param oldFile
     * @param newFile
     * @throws IOException
     */
    public static void copyFile(String oldFile, String newFile) throws IOException {
        File srcFile = new File(oldFile);
        File targetFile = new File(newFile);
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(targetFile);
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOHelper.close(in, out);
        }
        System.out.println("文件复制成功");


    }

    /**
     * 返回文件名
     * @param fileUrl 文件路径
     * @return 文件名
     */
    public static String retFileName(String fileUrl){
        if(fileUrl==null || fileUrl.isEmpty()) {
            return "";
        }
        File tempFile = new File(fileUrl.trim());
        return tempFile.getName();
    }

    public static void main(String[] args) throws IOException, BizException {
//		String classPath = StudentCard.class.getResource("").getPath();
//		String projectPath = System.getProperty("user.dir");
//		System.out.println(classPath);
//		System.out.println(projectPath);
        //copyFile("E:\\吴沼淇\\1.jpg", System.getProperty("user.dir") + "\\img\\1.jpg");
        System.out.println(retFileName("D:\\吴沼淇\\1.jpg"));
    }
}
