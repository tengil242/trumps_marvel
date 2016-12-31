package se.swdevsupport.trumps.marvel.backbone;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

/**
* La classe Product est un objet JAVA contenant une liste de produits.
* <p/>
* En ajoutant l'annotation @XmlRootElement, nous offrons la possibilité à JAXB de transformer cet objet en document XML et inversement.
*
* <Products>
*     <Product>
*         <id>10010</id>
*         <description>Produit 1</description>
*     </Product>
*    <Product>
*         <id>10011</id>
*         <description>Produit 2</description>
*    </Product>
* <Products>
*/

@XmlRootElement(name = "Products")
@XmlAccessorType (XmlAccessType.FIELD)
public class Products 
{
     @XmlElement(name = "Product")
     private List<Product> productsList = null;
  
     public List<Product> getProducts() 
     {
         return productsList;
     }
 
     public void setProducts(List<Product> productsList)
     {
        this.productsList = productsList;
     }
}
