/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LectorLitigios;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Debml
 */
public class LectorLitigios {
    
    public String dateToday = "15-07-2015";
    public Date today = new Date(dateToday);
    
    public boolean compareDate(String date){
        return today.isGreater(date);
    }
    
    //Falta crearlo dinamicamente
    /**public String createURL(int i){
        String URL;
        
        if(i == 1)
            URL = "http://www.dgepj.cjf.gob.mx/internet/expedientes/ExpedienteyTipo.asp?TipoAsunto=10&TipoProcedimiento=979&Expediente=155%2F2015&Buscar=Buscar&Circuito=4&CircuitoName=Cuarto+Circuito&Organismo=506&OrgName=Tercer+Tribunal+Colegiado+en+Materia+Civil+del+Cuarto+Circuito&TipoOrganismo=0&Accion=1";
        else if(i == 2)
            URL = "http://www.dgepj.cjf.gob.mx/internet/expedientes/ExpedienteyTipo.asp?TipoAsunto=1&TipoProcedimiento=979&Expediente=99%2F2014&Buscar=Buscar&Circuito=4&CircuitoName=Cuarto+Circuito&Organismo=696&OrgName=Juzgado+Primero+de+Distrito+en+Materia+Civil+y+de+Trabajo+en+el+Estado+de+Nuevo+Le%F3n&TipoOrganismo=0&Accion=1";
        else if(i == 3)
            URL = "http://www.dgepj.cjf.gob.mx/internet/expedientes/ExpedienteyTipo.asp?TipoAsunto=1&TipoProcedimiento=979&Expediente=1561%2F2014&Buscar=Buscar&Circuito=4&CircuitoName=Cuarto+Circuito&Organismo=562&OrgName=Juzgado+Primero+de+Distrito+en+Materia+Administrativa+en+el+Estado+de+Nuevo+Le%F3n&TipoOrganismo=0&Accion=1";        
        else
            URL = "http://www.dgepj.cjf.gob.mx/internet/expedientes/ExpedienteyTipo.asp?TipoAsunto=1&TipoProcedimiento=979&Expediente=963%2F2014&Buscar=Buscar&Circuito=4&CircuitoName=Cuarto+Circuito&Organismo=569&OrgName=Juzgado+Tercero+de+Distrito+en+Materias+Civil+y+de+Trabajo+en+el+Estado+de+Nuevo+Le%F3n&TipoOrganismo=0&Accion=1";
     
        return URL;
    }**/
    
    //arreglar exceptions
    //Recorre el primer URL para conseguir el que esta dentro del iframe
    public String getMainURL(String URL_1){
        
        Document website = null;
        String srcURL = null;
        
        try{
            website = Jsoup.connect(URL_1).get();
            
            //busca el iframe tag
            Element iframe = website.getElementById("ifr");
        
            //consigue el URL del iframe
            srcURL = iframe.attr("src");
        
            //regresa el segundo URL
            return srcURL;
        }
        catch(IOException e){
            System.out.println("Error en el URL " + URL_1);
        }
        
        //Regresa null si hubo un error
        return srcURL;
    }
    
    //arreglar exceptions
    //recorre el segundo url para comparar fechas
    public boolean newPublication(String srcURL){
        
        Document webInfo = null;
        
        try{
            webInfo = Jsoup.connect(srcURL).get();
            
            //Busca la tabla del final
            Element table = webInfo.getElementById("grvAcuerdos");

            //Consigue todo el texto de la tabla
            Elements tds = table.getElementsByTag("td");
        
            //Itera solamente en las fechas de publicacion
            for (int i = 3; i < tds.size(); i = i + 6) {
                //Checa si hay fecha de publicacion nueva
                if(!compareDate(tds.get(i).text())){
                    return true;
                }
            }
        }
        catch(IOException e){
            //MARCAR PRIMER URL
            System.out.println("Error en URL ");
        }
        
        //No hubo fecha nueva
        return false;
    }
    
    //manda a llamar los metodos
    public void run() throws IOException{

        BufferedReader br = null;
        try{	
           br = new BufferedReader(new FileReader("/Users/Debml/Documents/Codes/Lector Litigios/urls1.txt"));		
	   String URL_1 = br.readLine();
           
           //por cada url (cada caso)
	   while (URL_1 != null) {
               //consigue el segundo url
               String srcURL = getMainURL(URL_1);
        
               if(srcURL != null){
                   //Compara fechas
                    if(newPublication(srcURL)){
                        System.out.println("Nueva publicación en el URL: " + URL_1);
                    }
                    else{
                        System.out.println
                            ("No hay nueva publicación en el URL: " + URL_1);
                    }
                }

                URL_1 = br.readLine();
	   }
        } 
        catch (IOException ioe){
            System.out.println("Error en el path/nombre del archivo");
        }
        catch (IllegalArgumentException e){
            System.out.println("Error en el contenido del archivo");
        }


        //String URL_1 = createURL(i);

        /**
        String srcURL = getMainURL(URL_1);
        
        if(newPublication(srcURL)){
            System.out.println("New publication");
        }
        else{
            System.out.println("No new publication");
        }**/
    
    }
    
    //Inicializa el run
    public static void main(String[] args) throws IOException {
        LectorLitigios program = new LectorLitigios();
        program.run();
    }
       
}
