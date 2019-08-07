package cn.sweetz.exercise.springbootdemo.common.utils;

import java.util.*;

/**
 * @author Administrator
 */
public class StringUtils {

    interface RandomType{
        String RANDOM_CHAR = "1";
        String RANDOM_NUMBER = "2";
        String RANDOM_CHAR_NUMBER = "3";
    }

    public static final String COMMA = ",";
    public static final String UNDERLINE = "_";

    private static final String EMPTY_STRING = "";
    private static final String[] STRINGARRAY = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final Integer CHARNUM = 52;
    private static final Integer NUMBERNUM = 10;
    private static final Integer TOTALNUM = CHARNUM + NUMBERNUM;


    /**
     * 驼峰命名法字符串转为下划线
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || EMPTY_STRING.equals(param.trim())) {
            return EMPTY_STRING;
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < len ; i++){
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 删除左边的字符串
     * @param target
     * @param removeStr
     * @return
     */
    public static String removeLeftStr(String target , String removeStr){
        if(target != null && target.startsWith(removeStr)){
            target = target.substring(removeStr.length());
            target = removeLeftStr(target , removeStr);
        }
        return target;
    }

    /**
     * 删除右边的字符串
     * @param target
     * @param removeStr
     * @return
     */
    public static String removeRightStr(String target , String removeStr){
        if(target != null && target.endsWith(removeStr)){
            target = target.substring(0 , target.length() - removeStr.length());
            target = removeRightStr(target , removeStr);
        }
        return target;
    }

    /**
     * 删除字符串两边的逗号（,）
     * @param target
     * @return
     */
    public static String removeComma(String target){
        return removeStr(target , COMMA);
    }
    /**
     * 删除两边的字符串
     * @param target
     * @param removeStr
     * @return
     */
    public static String removeStr(String target , String removeStr){
        return  removeStr(target , removeStr , removeStr);
    }
    /**
     * 删除两边的字符串
     * @param target
     * @param leftRemoveStr 左边需要删除的字符串
     * @param rightRemoveStr 右边需要删除的字符串
     * @return
     */
    public static String removeStr(String target , String leftRemoveStr , String rightRemoveStr){
        target = removeLeftStr(target , leftRemoveStr);
        target = removeRightStr(target , rightRemoveStr);
        return  target;
    }

    /**
     * 翻转字符串
     * @param reversalStr
     * @return
     */
    public static String reversal(String reversalStr){
        int length = reversalStr.length();
        int split = 2;
        if(length == 1){
            return reversalStr;
        }
        char[] chars = reversalStr.toCharArray();
        for(int index = 0 ; index < length / split ; index++){
            char t = chars[index];
            chars[index] = chars[length - index - 1];
            chars[length - index - 1] = t;
        }
        return new String(chars);
    }

    /**
     * 随机在字符与数字中生成字符串
     * @param bit 字符串长度
     * @return
     */
    public static String random(int bit){
        if(bit == 0){
            return EMPTY_STRING;
        }
        StringBuilder sb = new StringBuilder(bit);
        randomCommon(sb , TOTALNUM , bit);
        return sb.toString();
    }

    /**
     * 随机生成字符串
     * @param bit 位数
     * @param type 类型：纯字符、纯数字、混合
     * @return
     */
    public static String random(int bit , String type){
        if (bit == 0){
            return EMPTY_STRING;
        }
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        switch (type){
            case RandomType.RANDOM_CHAR :{
                randomCommon(sb , CHARNUM , bit);
                break;
            }
            case RandomType.RANDOM_NUMBER :{
                for (int i = 0 ; i < bit ; i++){
                    int index =  random.nextInt(NUMBERNUM);
                    sb.append(STRINGARRAY[CHARNUM + (index == NUMBERNUM ? index - 1 : index)]);
                }
                break;
            }
            default:{
                sb.append(random(bit));
                break;
            }
        }
        return sb.toString();
    }

    private static void randomCommon(StringBuilder sb , Integer size , int bit){
        Random random = new Random();
        for (int i = 0 ; i < bit ; i++){
            int index =  random.nextInt(size);
            sb.append(STRINGARRAY[index == size ? index - 1 : index]);
        }
    }

    /**
     * 字符串转换为集合
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> List<T> string2List(String str , Class<T> clazz){
        return string2List(str , COMMA , clazz);
    }

    /**
     * 字符串转换为集合
     * @param str
     * @param separator
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> List<T> string2List(String str , String separator , Class<T> clazz){
        if (EMPTY_STRING.equals(str) || str == null) {
            return null;
        }
        if(checkClass(clazz)){
            String[] array = str.split(separator);
            if(clazz.isAssignableFrom(Integer.class)){
                List<Integer> list = new ArrayList<>(array.length);
                Arrays.asList(array).forEach(item ->{
                    list.add(Integer.parseInt(item));
                });
                return (List<T>) list;
            }else if(clazz.isAssignableFrom(Long.class)){
                List<Long> list = new ArrayList<>(array.length);
                Arrays.asList(array).forEach(item ->{
                    list.add(Long.parseLong(item));
                });
                return (List<T>) list;
            }else if(clazz.isAssignableFrom(String.class)){
                List<String> list = new ArrayList<>(array.length);
                Arrays.asList(array).forEach(item ->{
                    list.add(item);
                });
                return (List<T>) list;
            }else if(clazz.isAssignableFrom(Double.class)){
                List<Double> list = new ArrayList<>(array.length);
                Arrays.asList(array).forEach(item ->{
                    list.add(Double.parseDouble(item));
                });
                return (List<T>) list;
            }else if(clazz.isAssignableFrom(Float.class)){
                List<Float> list = new ArrayList<>(array.length);
                Arrays.asList(array).forEach(item ->{
                    list.add(Float.parseFloat(item));
                });
                return (List<T>) list;
            }else if(clazz.isAssignableFrom(Boolean.class)){
                List<Boolean> list = new ArrayList<>(array.length);
                Arrays.asList(array).forEach(item ->{
                    list.add(Boolean.parseBoolean(item));
                });
                return (List<T>) list;
            }
        }else{
            throw new RuntimeException("不支持此类型");
        }
        return null;
    }

    private static boolean checkClass(Class<?> clazz){
        return clazz.isAssignableFrom(Integer.class)
                || clazz.isAssignableFrom(String.class)
                || clazz.isAssignableFrom(Long.class)
                || clazz.isAssignableFrom(Double.class)
                || clazz.isAssignableFrom(Float.class)
                || clazz.isAssignableFrom(Boolean.class);
    }

    /**
     * 拼接字符串 默认以","隔开
     * @param args
     * @return
     */
    public static String concat(String... args ){
        return concat(COMMA , args);
    }

    /**
     * 拼接字符串
     * @param separator
     * @param args
     * @return
     */
    public static String concat(String separator , String... args ){
        if(args == null || args.length == 0){
            return EMPTY_STRING;
        }
        return list2String(Arrays.asList(args) , separator);
    }

    /**
     * 集合转字符串 默认以","隔开
     * @param list
     * @param <T>
     * @return
     */
    public static <T> String list2String(Collection<T> list){
        return list2String(list,COMMA);
    }

    /**
     * 集合转字符串
     * @param list 集合
     * @param separator 分隔符
     * @param <T> 类型
     * @return
     */
    public static <T> String list2String(Collection<T> list , String separator){
        return list2String(list,separator ,EMPTY_STRING,EMPTY_STRING);
    }

    /**
     * 集合转字符串
     * @param list 集合
     * @param separator 分隔符
     * @param prefix 前缀
     * @param suffix 后缀
     * @param <T> 类型
     * @return
     */
    public static <T> String list2String(Collection<T> list , String separator, String prefix, String suffix){
        StringBuilder sb = new StringBuilder();
        if(!list.isEmpty()){
            Iterator<T> iterator = list.iterator();
            while (iterator.hasNext()){
                sb.append(separator).append(prefix).append(iterator.next()).append(suffix);
            }
            sb.delete(0,separator.length()).toString();
        }
        return sb.toString();
    }
}
