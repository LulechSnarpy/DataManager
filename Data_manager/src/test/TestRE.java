package test;


public class TestRE {
	public static void main(String[] args) {
		String st = "suv";
		String st2 = "bus";
		String regex = "^((suv)|(mpv)|(taxi)|(van)|(pickup)|(non-motor)|(unspecified))$";
		st = st.replaceAll(regex,"dog");
		st2 = st2.replaceAll(regex, "dog");
		System.out.println(st);
		System.out.println(st2);
	}
}
