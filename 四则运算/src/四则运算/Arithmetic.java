package ��������;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * ����������ʽ���� 
 * @see ���ܣ�������arithmetic����һ��double���͵Ľ������calculate�������ɡ�����ΰ�������˾��Ƚ��ĳЩBUG��
 * @author penli 
 */  
public class Arithmetic {  
    public static double arithmetic(String exp){  
        String result = parseExp(exp).replaceAll("[\\[\\]]", "");  
        return Double.parseDouble(result);  
    }  
    /** 
     * ������������������ʽ������2+((3+4)*2-22)/2*3 
     * @param expression 
     * @return String
     */  
    public static String parseExp(String expression){  
        //String numberReg="^((?!0)\\d+(\\.\\d+(?<!0))?)|(0\\.\\d+(?<!0))$";  
        expression=expression.replaceAll("\\s+", "").replaceAll("^\\((.+)\\)$", "$1");  
        String checkExp="\\d";  
        String minExp="^((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\+\\-\\*\\/]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))$";  
        //��С���ʽ����  
        if(expression.matches(minExp)){  
            String result=calculate(expression);  
              
            return Double.parseDouble(result)>=0?result:"["+result+"]";  
        }  
        //���㲻�����ŵ���������  
        String noParentheses="^[^\\(\\)]+$";  
        String priorOperatorExp="(((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\*\\/]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\])))";  
        String operatorExp="(((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\+\\-]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\])))";  
        if(expression.matches(noParentheses)){  
            Pattern patt=Pattern.compile(priorOperatorExp);  
            Matcher mat=patt.matcher(expression);  
            if(mat.find()){  
                String tempMinExp=mat.group();  
                expression=expression.replaceFirst(priorOperatorExp, parseExp(tempMinExp));  
            }else{  
                patt=Pattern.compile(operatorExp);  
                mat=patt.matcher(expression);  
                  
                if(mat.find()){  
                    String tempMinExp=mat.group();  
                    expression=expression.replaceFirst(operatorExp, parseExp(tempMinExp));  
                }  
            }  
            return parseExp(expression);  
        }  
        //��������ŵ���������  
        String minParentheses="\\([^\\(\\)]+\\)";  
        Pattern patt=Pattern.compile(minParentheses);  
        Matcher mat=patt.matcher(expression);  
        if(mat.find()){  
            String tempMinExp=mat.group();  
            expression=expression.replaceFirst(minParentheses, parseExp(tempMinExp));  
        }  
        return parseExp(expression);  
    }  
    /** 
     * ������С��λ����������ʽ���������֣� 
     * @param exp 
     * @return String
     */  
    public static String calculate(String exp){  
        exp=exp.replaceAll("[\\[\\]]", "");  
        String number[]=exp.replaceFirst("(\\d)[\\+\\-\\*\\/]", "$1,").split(",");  
        BigDecimal number1=new BigDecimal(number[0]);  
        BigDecimal number2=new BigDecimal(number[1]);  
        BigDecimal result=null;  
        /*
         * ���þ��ȣ����򱨴����������֪��Ҫ������λ�����Ժܸɴ�ı���
         * By ����ΰ
         */
        MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
        //����Ϊ2������ģʽΪ����0.5��1������������ 
        
        String operator=exp.replaceFirst("^.*\\d([\\+\\-\\*\\/]).+$", "$1");  
        if("+".equals(operator)){  
            result=number1.add(number2);  
        }else if("-".equals(operator)){  
            result=number1.subtract(number2);  
        }else if("*".equals(operator)){  
            result=number1.multiply(number2);  
        }else if("/".equals(operator)){  
            result=number1.divide(number2,mc);  
        }  
          
        return result!=null?result.toString():null;  
    }  
}  
