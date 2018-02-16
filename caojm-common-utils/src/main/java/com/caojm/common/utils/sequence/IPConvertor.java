package com.caojm.common.utils.sequence;

/**
 * 机器ip数据类型转换
 * IP地址是一个以点作为分隔符的十进制四字段字符串，例如“10.0.3.193”。将这四个十进制数转化为二进制即为：
 *
 * 每段数字             相对应的二进制数
 * 10                          00001010
 * 0                            00000000
 * 3                            00000011
 * 193                        11000001

 * 以从左到右的顺序放在一起，为00001010 00000000 00000011 11000001，转换为10进制数就是：167773121，即为一个长整型。
 * 从长整型到字符串的转化要点：移位、屏蔽掉不需要的位，字符串拼接。
 * 从字符串到长整型的转化要点：解析字符串，移位，求和。
 *
 * @author <a href="mailto:caojianmin@jd.com">caojianmin</a>
 * @create 2018/02/15 18:16
 */
public class IPConvertor {

    public static String toStr(long ip) {
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            sb.append((ip >>> (i * 8)) & 0x000000ff);
            if (i != 0) {
                sb.append('.');
            }
        }
        System.out.println("the ip string:"+sb);   //输出例如:5.128.122.10
        return sb.toString();

    }

    public static long toLong(String ip) {
        long num = 0;
        String[] sections = ip.split("\\.");
        int i = 3;
        for (String str : sections) {
            num += (Long.parseLong(str) << (i * 8));    //每次左移8位
            i--;
        }
          System.out.println("convert ip to long:"+num);    //输出例如 92305930
        return num;
    }

    public static String toBinary(long ip){
        String binaryIp = Long.toBinaryString(ip);
        System.out.println("convert ip to binary:"+binaryIp);
        return binaryIp;
    }

    public static void main(String[] args) {
        long sd = 92305930;
        String str = toStr(sd);
        toBinary(toLong(str));
        System.out.println(toBinary(toLong("192.168.1.108")).substring(22));

    }
}
