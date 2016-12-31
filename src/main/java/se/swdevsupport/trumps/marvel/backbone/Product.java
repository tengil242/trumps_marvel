package se.swdevsupport.trumps.marvel.backbone;

import javax.xml.bind.annotation.XmlRootElement;

/**
* La classe Product est un objet JAVA contenant des méthodes get et set
* <p/>
* En ajoutant l'annotation @XmlRootElement, nous offrons la possibilité à JAXB de transformer cet objet en document XML et inversement.
* <p/>
* La représentation XML d'un Product ressemblera à ceci :
* 
* <Product>
* <id>10010</id>
* <description>produit 1</description>
* </Product>
*/
@XmlRootElement(name = "Product")
public class Product 
{
private long id;
private String description;

public long getId() 
{
   return id;
}

public void setId(long id) 
{
   this.id = id;
}

public String getDescription() 
{
   return description;
}

public void setDescription(String d) 
{
   this.description = d;
}
}