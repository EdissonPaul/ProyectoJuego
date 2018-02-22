package Utilidades;

public class testDistance {

	public static void main(String[] args) {

	      String str1 = "identificar";
	      String str2 = "identify";

	      LevenshteinDistance ld = new LevenshteinDistance();
	      ld.setWords(str1, str2);

	      // Mostrar resultados
	      System.out.println("Palabra1: " + str1);
	      System.out.println("Palabra2: " + str2);
	      System.out.println("\nDistancia de Levenshtein:\n" + ld.getDistancia());
	      System.out.println("Afinidad:\n" + ld.getAfinidad() * 100 + " %");
	      String str3="";
	      String str4="";
	      if((ld.getAfinidad() * 100)==50) {
	    	  if(str1.length()>str2.length()) {
	    		  str3=str1.substring(str1.length()/2);
	    		  str4=str2;
	    	  }else {
	    		  str3=str2.substring(str2.length()/2);
	    		  str4=str1;
	    	  }
	    		if (str3.equals(str4)) {
	    		      System.out.println("correcto");

					
				} else {
					System.out.println("correcto");

				}
	      }

	}

}
