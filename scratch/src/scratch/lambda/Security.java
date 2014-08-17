package scratch.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Security {

	public enum Type {
			INDEX, EQUITY
	}
	static final char doublequotes='"';
	static final char comma=',';
	
	String ticker;
	String name;
	float price;
	Type type;
	
	interface Tester{
		public boolean test(Security security);
	}
	/**
	interface Predicate<Security> {
	    boolean test(Security t);
	}
	*/
	public Security(String ticker,String name,float price,Type type) {
		 this.ticker=ticker;
		 this.name=name;
		 this.price=price;
		 this.type=type;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("{")
		.append(doublequotes).append("ticker").append(doublequotes).append(":").append(doublequotes).append(ticker).append(doublequotes).append(comma)
		.append(doublequotes).append("name").append(doublequotes).append(":").append(doublequotes).append(name).append(doublequotes).append(comma)		
		.append(doublequotes).append("price").append(doublequotes).append(":").append(price).append(comma)
		.append(doublequotes).append("type").append(doublequotes).append(":").append(doublequotes).append(type).append(doublequotes)	
		.append("}\n");
		return sb.toString();
	}
	public void print(){
		System.out.println(this.toString());
	}
	
	
	static public List<Security> selectSecurities(List<Security> securities,Tester tester){
		List<Security> result = new ArrayList<Security>();
		for (Security security:securities){
			if (tester.test(security))result.add(security);
		}
		return result;
	}
	
	static public List<Security> selectSecuritiesWithPredicate(List<Security> securities,
															   Predicate<Security> tester){
		List<Security> result = new ArrayList<Security>();
		for (Security security:securities){
			if (tester.test(security))result.add(security);
		}
		return result;
	}
	static public void selectSecuritiesWithPredicate2(List<Security> securities,
			   Predicate<Security> tester, Consumer<Security> block){
					 
					for (Security security:securities){
						if (tester.test(security))block.accept(security);
					}

		}
 	
	
	static public void main(String[]args){
		Security APPL = new Security("APPL","Apple Inc",450,Type.EQUITY);
		Security MSFT = new Security("MSFT","Microsoft",30,Type.EQUITY);		
		Security SPX500 = new Security("SPX500","Index 500",1030,Type.INDEX);
		Security DJ = new Security("DJ","Dow Jones",17000,Type.INDEX);
		
		List<Security> securities = new ArrayList<Security>();
		securities.add(APPL);
		securities.add(MSFT);		
		securities.add(SPX500);	
		securities.add(DJ);	
		
		System.out.println(securities);
		List<Security> indices = selectSecurities(securities,new Tester(){
								public boolean test(Security security){
									return security.type==Type.INDEX;
								}
				});
		System.out.println(indices);	
		
		List<Security> indicesWithLambda = selectSecurities(securities,
								 (Security security) ->security.type==Type.INDEX);
		
		System.out.println(indicesWithLambda);		

		List<Security> indicesWithLambdaWithPredicate = selectSecuritiesWithPredicate(securities,
				 (Security security) ->security.type==Type.INDEX);

		System.out.println(indicesWithLambdaWithPredicate);	
	
		List<Security> indicesWithLambdaWithPredicate2 = selectSecuritiesWithPredicate(securities,
				 (Security security) ->security.type==Type.INDEX&& security.price<5000);
		System.out.println(indicesWithLambdaWithPredicate2);	
		
		

		
		selectSecuritiesWithPredicate2(securities, 
												security ->security.type==Type.INDEX,
												security ->security.print());
	 
		selectSecuritiesWithPredicate2(securities, 
				security -> security.type==Type.INDEX,
				security -> TestConsumer.sayHello(security));
		
		selectSecuritiesWithPredicate2(securities, 
				security -> security.type==Type.INDEX,
				security -> TestConsumer.sayHello(security));

		List<Security> result = new ArrayList<Security>();		
		selectSecuritiesWithPredicate2(securities, 
				security -> security.type==Type.INDEX,
				security -> result.add(security));

		System.out.println(result);
 
	}
	
}
