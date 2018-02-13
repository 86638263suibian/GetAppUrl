package com.banyan.getappurl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Util {

	/**
	 * �ֽ�תchar
	 * 
	 * @param bytes
	 * @return
	 */
	public static char byteToChar(byte bytes) {
		Charset cs = Charset.forName("UTF-8");
		ByteBuffer bb = ByteBuffer.allocate(1);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);
		char[] tmp = cb.array();
		return tmp[0];
	}

	/**
	 * byte����תchar����
	 * 
	 * @param bytes
	 * @return
	 */
	public static char[] byteToChars(byte[] bytes) {
		Charset cs = Charset.forName("UTF-8");
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);
		return cb.array();
	}

	/**
	 * С��byte[]תint
	 * 
	 * @param bytes
	 * @return
	 */
	public static int bytesToInt(byte[] bytes) {
		int value = 0;
		for (int i = 0; i < bytes.length; i++) {
			value |= (bytes[i] & 0xFF) << (8 * i);
		}
		return value;
	}

	/**
	 * ����ƥ�����з���������url
	 * 
	 * @param regex
	 * @param source
	 * @return
	 */
	public static String regexUrl(String source) {
		String regex = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		StringBuilder urlList = new StringBuilder();
		while (matcher.find()) {
			urlList.append(matcher.group() + "\n");
			System.out.println(matcher.group());
		}
		return urlList.toString();
	}

	/**
	 * ����ƥ����Ҫ�����õ��ļ���·��
	 * 
	 * @param ·��
	 * @return
	 */
	public static String regexFile(String path) {
		String[] regexList = { "(classes){1,1}[a-z,A-z,0-9]*(.dex){1,1}", "(resources){1,1}[a-z,A-z,0-9]*(.arsc){1,1}",
				"(lib){1,1}[a-z,A-z,0-9]*(.so){1,1}" };
		String[] fileType = { "dex", "arsc", "so" };
		for (int i = 0; i < regexList.length; i++) {
			Pattern pattern = Pattern.compile(regexList[i]);
			Matcher matcher = pattern.matcher(path);
			if (matcher.find()) {
				System.out.println("���ڽ�ѹ��" + matcher.group());
				return fileType[i];
			}
		}
		return null;

	}

	/**
	 * ɾ���ļ��µ������ļ�
	 * 
	 * @param dir
	 *            ��Ҫɾ���ļ������������ݵ��ļ���
	 */
	public static void deleteDir(File dir) {
		File[] files = dir.listFiles();
		for (File f : files) {

			// 3.�ж��Ƿ���Ŀ¼������У�����ʹ�øù��ܱ�������������ļ��У�ֱ��ɾ��
			if (f.isDirectory()) {
				deleteDir(f);
			} else {
				f.delete();// �ļ�ɾ��
			}
		}
		dir.delete();// ���ɾ���ļ���

	}

	/**
	 * �����½����ļ��У����������ɾ���ļ�������������
	 * 
	 * @param destDirName
	 */
	public static void createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.isDirectory()) {
			deleteDir(dir);
			dir.mkdirs();
		} else {
			dir.mkdirs();
		}
	}

	/**
	 * ��ѹ�ļ���tempĿ¼
	 * 
	 * @param zeEntry
	 * @param zipFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void unZipToDir(ZipEntry zeEntry, ZipFile zipFile) throws FileNotFoundException, IOException {
		String fileName = zeEntry.getName();
		File file = new File("temp/" + fileName);
		if (!file.exists()) {
			File rootDirectoryFile = new File(file.getParent());
			// ����Ŀ¼
			if (!rootDirectoryFile.exists()) {
				boolean ifSuccess = rootDirectoryFile.mkdirs();
			}
			file.createNewFile();
		}
		// д���ļ�
		BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream(file));
		InputStream read = zipFile.getInputStream(zeEntry);
		int cha = 0;
		while ((cha = read.read()) != -1) {
			write.write(cha);
		}
		write.flush();
		write.close();
		read.close();
	}

}
