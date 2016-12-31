/**
 * 
 */
package se.swdevsupport.trumps.marvel.backbone;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * La classe OrderBean est un objet JAVA contenant des méthodes get et set et est aussi utilisée pour les commandes retournées par  
 * la classe CardServiceImpl.
 * <p/>
 * En ajoutant l'annotation @XmlRootElement, nous offrons la possibilité à JAXB de transformer cet objet en document XML et inversement.
 * <p/>
 * La représentation XML pour un OrderBean est la suivante :
 * <Order>
 * <id>223</id>
 * <description>Order 223</description>
 * </Order>
 */

@XmlRootElement(name = "Order")
public class OrderBean 
{
	private static final Logger LOG = LoggerFactory.getLogger(CardService.class);

	private long id;
	private String description;
	//private Map<Long, Product> products = new HashMap<Long, Product>();
	private Products products = new Products();

	public OrderBean() 
	{
		init();
	}

	final void init() 
	{
		products.setProducts(new ArrayList<Product>());
		Product p1 = new Product();
		p1.setId(323);
		p1.setDescription("product 323");

		Product p2 = new Product();
		p2.setId(324);
		p2.setDescription("product 324");

		products.getProducts().add(p1);
		products.getProducts().add(p2);
	}

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

	public Product getProduct(int productId)
	{
		Product prod = null;
		for(Product p : products.getProducts())
		{
			if(p.getId()==productId)
			{
				prod = p;
			}
		}
		return prod;
	}

	public Products getProducts()
	{
		return products;
	}
}
