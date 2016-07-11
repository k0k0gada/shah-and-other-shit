package uprajneniqOtKnijkata;

public class tryThis {
	public static void main(String[] args) {
		String s1 = new String("kolkoto");
		String s2 = s1;
		System.out.println(s1 == s2);
		System.out.println();
		String s3 = "asd";
		String s4 = s3;
		System.out.println(s3 == s4);
		System.out.println();
		String s5="asd";
		String s6=new String(s5);
		System.out.println(s5==s6);
	}
}
