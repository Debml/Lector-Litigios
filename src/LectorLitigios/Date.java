/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectorLitigios;

/**
 * Date
 *
 * Generic datatype for Dates expressed as DD-MM-YYYY
 *
 * @author David Benitez
 * @version 1.0
 * @date 17/07/2015
 */
public class Date {
    
    private int iDay;
    private int iMonth;
    private int iYear;
    
    /**
     * Date
     * 
     * Date constructor based on 3 parameters
     * 
     * @param iDay is an <code>int</code> which represents the day
     * @param iMonth is an <code>int</code> which represents the month
     * @param iYear is an <code>int</code> which represents the year
     */
    public Date(int iDay, int iMonth, int iYear) {
        this.iDay = iDay;
        this.iMonth = iMonth;
        this.iYear = iYear;
    }
    
    /**
     * Date
     * 
     * Date constructor based on a string
     * 
     * @param strDate is a <code>string</code> with the date in the 
     * form of DD-MM-YYY
     */
    public Date(String strDate) {
        iDay = Integer.parseInt(strDate.substring(0,2));
        iMonth = Integer.parseInt(strDate.substring(3,5));
        iYear = Integer.parseInt(strDate.substring(6,10));
    }
        
    /**
     * getDay
     * 
     * Returns the numerical value for the day
     * 
     * @return<code>int</code> with the day
     */
    public int getDay(){
        return iDay;
    }
    
    /**
     * getMonth
     * 
     * Returns the numerical value for the month
     * 
     * @return<code>int</code> with the month
     */
    public int getMonth(){
        return iMonth;
    }
        
    /**
     * getYear
     * 
     * Returns the numerical value for the Year
     * 
     * @return<code>int</code> with the year
     */
    public int getYear(){
        return iYear;
    }
    
    /**
     * setDay
     * 
     * sets a numerical value for the day
     * 
     * @param iDay is an <code>int</code> with the day
     */
    public void setDay(int iDay){
        this.iDay = iDay;
    }
    
    /**
     * setMonth
     * 
     * sets a numerical value for the month
     * 
     * @param iMonth is an <code>int</code> with the month
     */
    public void setMonth(int iMonth){
        this.iMonth = iMonth;
    }
    
    /**
     * setYear
     * 
     * sets a numerical value for the year
     * 
     * @param iYear is an <code>int</code> with the year
     */
    public void setYear(int iYear){
        this.iYear = iYear;
    }
    
    /**
     * toString
     * 
     * Overriden method that converts a Date to a string in the form of
     * DD-MM-YYYY
     * 
     * @return a string with the date
     */
    @Override
    public String toString(){
        String date = "";
        date += Integer.toString(iDay);
        date += "-";
        date += Integer.toString(iMonth);
        date += "-";
        date += Integer.toString(iYear);
                
        return date;
    }
    
    /**
     * printDate
     * 
     * Method that prints a Date to a string in the form of DD-MM-YYYY
     * 
     */
    public void printDate(){
        String output = "";
        if(iDay < 10){
            output += Integer.toString(0);
        }
        output += iDay + "-";
        
        if(iMonth < 10){
            output += Integer.toString(0);
        }
        output += iMonth + "-";
        
        output += iYear;
        
        System.out.println(output);
    }
    
    /**
     * isGreater
     * 
     * Method that compares two dates
     * 
     * @param date2 is a <code>Date</code> to be compared
     * @return  if the object date is greater than the one received
     */
    public boolean isGreater(Date date2){
        //If the year is bigger
        if(this.iYear > date2.iYear){
            return true;
        }
        //If the year is smaller
        else if(this.iYear < date2.iYear){
            return false;
        }
        //If same year
        else{
            //if the month is bigger
            if(this.iMonth > date2.iMonth){
                return true;
            }
            //if the month is smaller
            else if(this.iMonth < date2.iMonth){
                return false;
            }
            //If its the same month
            else{
                //If the day is bigger
                if(this.iDay > date2.iDay){
                    return true;
                }
                //If the day is smaller
                else if(this.iDay < date2.iDay){
                    return false;
                }
                //if the day is the same (same date)
                else{
                    return false;
                }
            }
        }
        
        
    }
    
    /**
     * isGreater
     * 
     * Method that compares two dates (calls overriden method above)
     * 
     * @param date2 is a <code>String</code> to be
     * @return if the object date is greater than the one received
     */
    public boolean isGreater(String date2){
        //creates a new Date
        Date dateAux = new Date(date2);
        
        //Calls overriden method
        return isGreater(dateAux);
    }
}
