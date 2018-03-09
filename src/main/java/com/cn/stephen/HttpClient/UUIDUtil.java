package com.cn.stephen.HttpClient;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
/**
 * 生成优惠码(使用guava工具包)
 * @author doraemon4
 *
 */
public class UUIDUtil {
	public static String str="abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static List<String> createDiscountCode(int couponSize){
		Set<String> couponList=Sets.newHashSet();
		while(couponList.size()<couponSize){
			String uuid=UUID.randomUUID().toString();
			uuid=uuid.replace("-", "");
			StringBuffer buffer=new StringBuffer();
			for(int i=0;i<uuid.length()/2;i++){
				String subStr=uuid.substring(i*2,i*2+2);
				char ch=str.charAt(Integer.parseInt(subStr,16)%0x3E);
				buffer.append(ch);
			}
			couponList.add(buffer.toString());
		}
		return Lists.newArrayList(couponList);
	}
	
	public static void main(String[] args) {
		System.out.println(UUIDUtil.createDiscountCode(10000));
	}
}
