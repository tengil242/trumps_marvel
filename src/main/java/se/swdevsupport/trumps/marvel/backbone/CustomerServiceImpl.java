/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.swdevsupport.trumps.marvel.backbone;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerServiceImpl implements CustomerService 
{
     private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

     long currentId = 123;
     Map<Long, CustomerBean> customers = new HashMap<Long, CustomerBean>();
     Map<Long, OrderBean> orders = new HashMap<Long, OrderBean>();

     public CustomerServiceImpl() 
     {
        init();
        }


  /**
   * Cette méthode est utilisée par le constructeur pour insérer un objet CustomerBean et un objet OrderBean dans la map locale pour tester.
   */
  final void init() 
  {
     LOG.info("Appel de la méthode init de CustomerServiceImpl");
     CustomerBean c = new CustomerBean();
     c.setName("Olivier");
     c.setId(123);
     customers.put(c.getId(), c);

     OrderBean o = new OrderBean();
     o.setDescription("order 223");
     o.setId(223);
     orders.put(o.getId(), o);
  }



  //code retour = 500: ID invalid
  //code retour = 204: customer non trouve 
  public CustomerBean getCustomer(String id)
  {
     LOG.info("Appel de getCustomer avec l'identifiant: {}", id);
     long idNumber = Long.parseLong(id);
     CustomerBean c = customers.get(idNumber);
     return c;
   }

 
   public Response updateCustomer(CustomerBean customer)
   {
     LOG.info("Mise à jour d'un client dont le nom est : {}", customer.getName());
     CustomerBean c = customers.get(customer.getId());
     Response r;
     if (c != null) 
     {
        customers.put(customer.getId(), customer);
        r = Response.ok().build();
     } 
     else 
     {
         r = Response.notModified().build();
     }

     return r;
   }


   public Response addCustomer(CustomerBean customer)
   {
     LOG.info("Ajout d'un client dont le nom est : {}", customer.getName());
     customer.setId(++currentId);

     customers.put(customer.getId(), customer);
     return Response.ok().type("application/xml").entity(customer).build();
   }


   public Response deleteCustomer(String id)
   {
     LOG.info("Suppression d'un client dont l'identifiant est : {}", id);
     long idNumber = Long.parseLong(id);
     CustomerBean c = customers.get(idNumber);

     Response r;
     if (c != null) 
     {
         r = Response.ok().build();
         customers.remove(idNumber);
     } 
     else 
     {
         r = Response.notModified().build();
     }

     return r;
   }


   public OrderBean getOrder(String orderId)
   {
     LOG.info("Récupération de la commande d'identifiant : {}", orderId);
     long idNumber = Long.parseLong(orderId);
     OrderBean c = orders.get(idNumber);
     return c;
   }


   public Product getProduct(String orderId, int productId)
   {
     LOG.info("Récupération de la commande: {}", orderId);
     long idNumber = Long.parseLong(orderId);
      OrderBean c = orders.get(idNumber);
     //return c;

     LOG.info("Récupération du produit d'identifiant : " + productId);
     Product p = c.getProduct(productId);
     return p;
   }

   public Products getProducts(String orderId)
   {
     LOG.info("Récupération des produits de la commande : {}", orderId);
     long idNumber = Long.parseLong(orderId);
     OrderBean c = orders.get(idNumber);
     //return c;

     Products p = c.getProducts();
     return p;
   }
}