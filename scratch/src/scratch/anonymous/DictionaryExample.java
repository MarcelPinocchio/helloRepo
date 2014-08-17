package scratch.anonymous;

// special comment here

public class DictionaryExample {

	 
	
	interface Dictionary{
		public String translate(String word); 
	}
	public DictionaryExample() {
		// TODO Auto-generated constructor stub
	
		}			
	static public void main(String[]args){
		final String WORD1="hello";
		final String WORD2="good bye";
		Dictionary frenchDictionary = new Dictionary(){
			public String translate(String word){		 
				if (word.equalsIgnoreCase(WORD1))return "bonjour";
				else if (word.equalsIgnoreCase(WORD2))return "au revoir";
				else return "Je ne comprends pas";
			}
		};

		System.out.println(frenchDictionary.translate("hello"));
		
		
	}
	
	}

