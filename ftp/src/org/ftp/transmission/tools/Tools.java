package org.ftp.transmission.tools;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ������
 * Created by Naga on 2015/11/11.
 */
public class Tools {
    public static class NetTools {

        public static String hostAddress() {
            String address = "";
            try {
                InetAddress addr =  InetAddress.getLocalHost();
                address = addr.getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            return address;
        }
    }

    /**
     * �ַ���������
     */
    public static class StringTools {
        public static boolean isEmpty(String label) {
            if (label == null || label.length() == 0) {
                return true;
            }
            return false;
        }

        public static String reversal(String label) {
            if (isEmpty(label)) {
                return "";
            }

            return new StringBuffer(label).reverse().toString();
        }

        public static boolean isPalindrome(String label) {
            if (isEmpty(label)) {
                return false;
            }

            return reversal(label).equals(label);
        }
    }

    public static class FileTools {

        /**
         * �ļ�������
         * @param path �ļ�Ŀ¼
         * @param oldName  ԭ�����ļ���
         * @param newName ���ļ���
         */
        public static void renameFile(String path, String oldName, String newName) {

            //�µ��ļ�������ǰ�ļ�����ͬʱ,���б�Ҫ����������
            if(!oldName.equals(newName)){
                File oldFile=new File(path + "/" + oldName);
                File newFile=new File(path + "/" + newName);

                // �������ļ�������
                if(!oldFile.exists()){
                    return;
                }

                //���ڸ�Ŀ¼���Ѿ���һ���ļ������ļ�����ͬ��������������
                if(newFile.exists()) {
                    System.out.println(newName + "�Ѿ�����.");
                } else{
                    oldFile.renameTo(newFile);
                }
            }else{
                System.out.println("���ļ����;��ļ�����ͬ");
            }
        }
    }
}
