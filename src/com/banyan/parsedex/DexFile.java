package com.banyan.parsedex;

public class DexFile {

	//dex�ļ�ͷ
	class DexHeader {
		byte[] magic = new byte[8]; //dex�ļ�ͷ
		byte[] checksum = new byte[4]; //У����
		byte[] signature = new byte[20]; //sha-1ǩ��, ȥ����magic��checksum��signature�ֶ�֮����������ݵ�ǩ��
		byte[] fileSize = new byte[4]; //dex�ļ���С
		byte[] headerSise = new byte[4]; //dexͷ��С
		byte[] endianTag = new byte[4]; //�ֽ���
		byte[] linkSize = new byte[4]; //���ӶεĴ�С, Ĭ��Ϊ0��ʾ��̬����
		byte[] linkOff = new byte[4]; //���Ӷο�ʼƫ��
		byte[] mapOff = new byte[4]; //map_itemƫ��
		byte[] stringIdsSite = new byte[4]; //�ַ����б��е��ַ�������
		byte[] stringIdsOff = new byte[4]; //	�ַ����б�ƫ��
		byte[] typeIdsSize = new byte[4]; //�����б��е����͸���
		byte[] typeIdsOff = new byte[4]; //�����б�ƫ�ơ�
		byte[] protoIdsSize = new byte[4]; 	//���������б��еĸ���
		byte[] protoIdsOff = new byte[4];		//���������б�ƫ��
		byte[] fieldIdsSize = new byte[4]; //�ֶ��б��еĸ���
		byte[] fieldIdsOff = new byte[4]; //�ֶ��б�ƫ��
		byte[] methodIdsSize = new byte[4]; //�����б��еĸ���
		byte[] methodIdsOff = new byte[4]; //�����б�ƫ��
		byte[] classDefsSize = new byte[4]; //�ඨ���б��еĸ���
		byte[] classDefsOff = new byte[4]; //�ඨ���б�ƫ��
		byte[] dataSize = new byte[4]; //���ݶεĴ�С, 4�ֽڶ���
		byte[] dataOff = new byte[4]; //	���ݶ�ƫ��
	}
	//String����������
	class DexStringIdAndItem{
		byte[] stringDataOff = new byte[4];
		byte[] stringSite = new byte[1];
		
	}
	//Type�����б�
	class DexTypeIds{
		
	}
	//����ԭ�������б�
	class DexProtoIds{
		
	}
	//�������б�
	class DexFields{
		
	}
	//���������б�
	class DexMethod{
		
	}

	

}
