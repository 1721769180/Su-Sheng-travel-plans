package ��������;

import java.util.Scanner;
public class Test1{
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int n = 10;
		int a, b;
		int sum = 0;
		System.out.println("����"+n+"���⣬������׼��:");
		//n = scanner.nextInt();
		int[] op = new int[n];
		int[] s = new int[n];
		int[] result = new int[n];
		String[] questions = new String[n];
		for (int i = 0; i < n; i++) {
			op[i] = (int) (Math.random() * 4); // �����
			a = (int) (Math.random() * 89) + 10; // ��a
			b = (int) (Math.random() * 89) + 10; // ��b
			System.out.println(a + getOp(op[i]) + b + "=?");
			System.out.println("�����:");
			s[i] = scanner.nextInt(); // ����Ĵ�
			result[i] = jisuan(a, b, op[i]); // ��ȷ��
			if (s[i] == result[i]) {
				sum++;
			}
			questions[i] = a + getOp(op[i]) + b + "=" + result[i];
		}
		// �������
		System.out.println("������" + sum + "���⣬�����" + (n - sum) + "����");
		for (int i = 0; i < n; i++) {
			System.out.print(questions[i]);
			if (s[i] == result[i]) {
				System.out.println(",��Ļش���" + s[i] + "����ȷ");
			} else {
				System.out.println(",��Ļش���" + s[i] + "������");
			}
		}
	}

	// ��ȡ�����
	private static String getOp(int index) {
		String string = "";
		switch (index) {
		case 0:
			string = "+";
			break;
		case 1:
			string = "-";
			break;
		case 2:
			string = "*";
			break;
		case 3:
			string = "/";
			break;
		}
		return string;
	}

	// �����
	private static int jisuan(int a, int b, int index) {
		int sum = 0;
		switch (index) {
		case 0:
			sum = a + b;
			break;
		case 1:
			sum = a - b;
			break;
		case 2:
			sum = a * b;
			break;
		case 3:
			sum = a / b;
			break;
		}
		return sum;
	}
}