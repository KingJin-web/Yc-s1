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
            try {
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Closeable... c) {
        for (Closeable closeable : c) {
            close(closeable);
        }
    }

    public static void close(AutoCloseable... c) {
        for (AutoCloseable autoCloseable : c) {
                close(autoCloseable);
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

        String path = newFile.substring(0, newFile.lastIndexOf("\\"));
        System.out.println(path);
        File file = new File(path);
        if (!file.exists()) {
            boolean b = file.mkdir();
        }
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
     *
     * @param fileUrl 文件路径
     * @return 文件名
     */
    public static String retFileName(String fileUrl) {
        if (fileUrl == null || fileUrl.isEmpty()) {
            return "";
        }
        File tempFile = new File(fileUrl.trim());
        return tempFile.getName();
    }

    public static void main(String[] args) throws IOException, BizException {

        copyFile("D:/Program Files/eclipse/plugins/org.eclipse.jface_3.15.100.v20190222-1334.jar",
                "H:\\jetbrains\\java\\Yc-s1\\lib\\swt\\org.eclipse.jface_3.15.100.v20190222-1334.jar");
    }


    /**
     * 删除文件，可以是单个文件或文件夹
     *
     * @param fileName 待删除的文件名
     */
    public static void delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败：" + fileName + "文件不存在");
        } else {
            if (file.isFile()) {
                deleteFile(fileName);
            } else {
                deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 被删除文件的文件名
     * @return 单个文件删除成功返回true, 否则返回false
     */
    public static boolean deleteFile( String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            System.out.println("删除单个文件" + fileName + "成功！");
            return true;
        } else {
            System.out.println("删除单个文件" + fileName + "失败！");
            return false;
        }
    }

    /**
     * 删除单个文件
     * @param dir 文件路径
     * @param fileName 被删除文件的文件名
     * @return 单个文件删除成功返回true, 否则返回false
     */
    public static boolean deleteFile(String dir, String fileName) {
        File file = new File(dir, fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            System.out.println("删除单个文件" + fileName + "成功！");
            return true;
        } else {
            System.out.println("删除单个文件" + fileName + "失败！");
            return false;
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param dir 被删除目录的文件路径
     * @return 目录删除成功返回true, 否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            System.out.println("删除目录失败" + dir + "目录不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            System.out.println("删除目录失败");
            return false;
        }

        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            System.out.println("删除目录" + dir + "失败！");
            return false;
        }
    }
    // 删除文件夹
    // param folderPath 文件夹完整绝对路径

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除指定文件夹下所有文件
    public static void delAllFile(String path) {

        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        assert tempList != null;
        for (String s : tempList) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + s);
            } else {
                temp = new File(path + File.separator + s);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + s);// 先删除文件夹里面的文件
                delFolder(path + "/" + s);// 再删除空文件夹
            }
        }
    }


}
