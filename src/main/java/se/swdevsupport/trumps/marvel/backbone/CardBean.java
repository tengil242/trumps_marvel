/**
 * 
 */
package se.swdevsupport.trumps.marvel.backbone;

/**
 * @author tengil
 *
 */
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "card")
public class CardBean 
{
private long id;
private String name;
private String realName;

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
public String getRealName() 
{
       return realName;
}

public void setRealName(String realName) 
{
      this.realName = realName;
}



}