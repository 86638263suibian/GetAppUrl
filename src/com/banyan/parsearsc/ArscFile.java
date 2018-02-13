package com.banyan.parsearsc;

public class ArscFile {

	//arscͷ��ṹ
	class ArscHeader{
		byte[] headType = new byte[2];
		byte[] headSize = new byte[2];
		byte[] fileSize = new byte[4];
		byte[] packageCount = new byte[4];
	}
	
	//ȫ���ַ����ؽṹ
	class GlobalStringPool{
		byte[] stringsType = new byte[2];
		byte[] stringsHeadSite = new byte[2];
		byte[] stringsSize = new byte[4];
		byte[] stringsCount = new byte[4];
		byte[] stylesCount = new byte[4];
		byte[] flag = new byte[4];
		byte[] stringsOff = new byte[4];
		byte[] styleOff = new byte[4];
	}

}
