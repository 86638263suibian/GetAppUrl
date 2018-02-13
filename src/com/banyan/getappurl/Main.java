package com.banyan.getappurl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.banyan.parsearsc.ParseArsc;
import com.banyan.parsedex.ParseDex;
import com.banyan.parseelf.ParseElf;

public class Main {
	
	static List<String> dexPaths = new ArrayList<>();
	static List<String> arscPaths = new ArrayList<>();
	static List<String> soPaths = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		if(args.length < 2) {
			Useage();
		}else {
			Title();
			String options = args[0];
			String appPath = args[1];
			switch(options) {
				case "-A":
					UnPack(appPath);
					ParseA();
					Util.deleteDir(new File("temp"));
					System.out.println("��ȡ��ɣ��ɵ�outputĿ¼�²鿴�����");
					break;
				case "-au":
					UnPack(appPath);
					ParseAU();
					Util.deleteDir(new File("temp"));
					System.out.println("��ȡ��ɣ��ɵ�outputĿ¼�²鿴�����");
					break;
				case "-du":
					UnPack(appPath);
					ParseDU();
					Util.deleteDir(new File("temp"));
					System.out.println("��ȡ��ɣ��ɵ�outputĿ¼�²鿴�����");
					break;
			}
		}
	}
	

	public static void Title() {
		System.out.println(
				"[>>>]              GetAppurl             [<<<]\n" +
				"[>>>]           code by Banyan           [<<<]\n" +
				"[>>>]                2018.2              [<<<]" 
				);
	}
	
	public static void Useage() {
		Title();
		System.out.println(
				"[*] Useage: java -jar GetAPPurl {Options} {target App}\n" +
				"[*] Options:                                          \n" +
				"[*]         -A: ��ȡdex��arsc��so��ȫ���ַ���                                                \n" +
				"[*]         -au: ��ȡdex��arsc��Url                 \n" +
				"[*]         -du: ��ȡdex�е�ȫ��url                       \n" 
				);
	}
	
	public static void UnPack(String apkPath) {
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(apkPath);
			Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
			String temp = "";
			while(enumeration.hasMoreElements()){
				ZipEntry zipEntry = (ZipEntry)enumeration.nextElement();
				temp = Util.regexFile(zipEntry.getName());
				if(null != temp) {
					switch(temp) {
						case "dex":
							dexPaths.add(zipEntry.getName());
							break;
						case "so":
							soPaths.add(zipEntry.getName());
							break;
						case "arsc":
							arscPaths.add(zipEntry.getName());
							break;
						default:
							break;
					}
					Util.unZipToDir(zipEntry,zipFile);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private static void ParseA() throws IOException {
		Util.createDir("output");
		for(String dexPath:dexPaths) {
			ParseDex parseDex = new ParseDex("temp/" +  dexPath);
			System.out.println("��ʼ��ȡ" + dexPath +"�������ַ���...");
			parseDex.getStrings();
		}
		for(String  arscPath: arscPaths) {
			ParseArsc parseArsc = new ParseArsc("temp/" +  arscPath);
			System.out.println("��ʼ��ȡ" + arscPath +"�������ַ���...");
			parseArsc.getStrings();
		}
		for(String  soPath: soPaths) {
			ParseElf parseElf = new ParseElf("temp/" +  soPath);
			System.out.println("��ʼ��ȡ" + soPath +"�������ַ���...");
			parseElf.getStrings();
		}
	}
	
	private static void ParseAU() throws IOException {
		Util.createDir("output");
		for(String dexPath:dexPaths) {
			ParseDex parseDex = new ParseDex("temp/" +  dexPath);
			System.out.println("��ʼ��ȡ" + dexPath +"������url...");
			parseDex.getUrl();
		}
		for(String  arscPath:arscPaths) {
			ParseArsc parseArsc = new ParseArsc("temp/" +  arscPath);
			System.out.println("��ʼ��ȡ" + arscPath +"������url...");
			parseArsc.getUrl();
		}
	}
	
	private static void ParseDU() throws IOException {
		Util.createDir("output");
		for(String dexPath:dexPaths) {
			ParseDex parseDex = new ParseDex("temp/" +  dexPath);
			System.out.println("��ʼ��ȡ" + dexPath +"������url...");
			parseDex.getUrl();
		}
	}
	
	
	
}
