package com.huantek.veinserver.methodTest;

public class annotationUsage {

    @AnnotationTest(name = "ceShi",method = enumTest.hello)
    @SuppressWarnings("123")
    public void test(){
        System.out.println("测试一下");
    }


    @Override
    public String toString(){
        return "重写";
    }


    public static void main1(String[] args) {
        annotationUsage usage = new annotationUsage();
        usage.test();
    }


    public static void main(String[] args) {
        String arg = "http://vein-server.oss-cn-shenzhen.aliyuncs.com\\Vein/Firmware/app.bin";
        String sub = arg.substring(7, 47);
        int length = arg.length();
        String sub2 = arg.substring(48, length);
                sub2 = "/"+sub2;
        System.out.println(sub);
        System.out.println(sub2);
        byte[] bytes = sub.getBytes();
        if (bytes.length<64) {
            byte[] arr = ByteZeroFill(bytes, 64);
        }
    }

    /**
     * 数组补零
     * @param bytes 需要补零的数组
     * @param len  最终长度
     * @return
     */
    public static byte[] ByteZeroFill(byte[] bytes , int len){
        byte[] arr = new byte[len];
        for (int i = 0;i<bytes.length;i++){
            arr[i] = bytes[i];
        }
        return arr;
    }
}
