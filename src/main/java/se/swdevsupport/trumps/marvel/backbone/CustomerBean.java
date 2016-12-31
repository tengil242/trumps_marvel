/**
 * 
 */
package se.swdevsupport.trumps.marvel.backbone;

/**
 * @author tengil
 *
 */
import javax.xml.bind.annotation.XmlRootElement;

/**
* La classe CustomerBean est un objet JAVA contenant des méthodes get et set
* <p/>
* En ajoutant l'annotation @XmlRootElement, nous offrons la possibilité à JAXB de transformer cet objet en document XML et inversement.
* <p/>
* La représentation XML d'un customer ressemblera à ceci :
* <Customer>
* <id>123</id>
* <name>Olivier</name>
* </Customer> 
*/
@XmlRootElement(name = "Customer")
public class CustomerBean 
{
private long id;
private String name;

public long getId() 
{
       return id;
}

public void setId(long id) 
{
       this.id = id;
}

public String getName() 
{
       return name;
}

public void setName(String name) 
{
      this.name = name;
}
}