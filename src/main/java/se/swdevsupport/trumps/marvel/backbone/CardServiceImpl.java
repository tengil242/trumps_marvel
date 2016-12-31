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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardServiceImpl implements CardService 
{
     private static final Logger LOG = LoggerFactory.getLogger(CardService.class);

     long currentId = 1;
     Map<Long, CardBean> cards = new HashMap<Long, CardBean>();
     Map<Long, OrderBean> orders = new HashMap<Long, OrderBean>();

     public CardServiceImpl() 
     {
        init();
        }


  /**
   * Cette méthode est utilisée par le constructeur pour insérer un objet CardBean et un objet OrderBean dans la map locale pour tester.
   */
  final void init() 
  {
     LOG.info("Entering method init in CardServiceImpl");
     CardBean c = new CardBean();
     c.setName("Iron Man");
     c.setRealName("Tony Stark");
     c.setId(1);
     cards.put(c.getId(), c);

     c = new CardBean();
     c.setName("The Hulk");
     c.setRealName("David Banner");
     c.setId(2);
     cards.put(c.getId(), c);

     
     OrderBean o = new OrderBean();
     o.setDescription("order 223");
     o.setId(223);
     orders.put(o.getId(), o);
  }



  //return code 500: ID invalid
  //return code 204: customer non trouve 
  public CardBean getCard(String id)
  {
     LOG.info("Getting card with id: {}", id);
     long idNumber = Long.parseLong(id);
     CardBean c = cards.get(idNumber);
     return c;
   }

  public CardBean getCards()
  {
     LOG.info("Getting all cards");
     long idNumber= 1;
     CardBean c = cards.get(idNumber);
     return c;
   }

  
   public Response updateCard(CardBean card)
   {
     LOG.info("Mise à jour d'un client dont le nom est : {}", card.getName());
     CardBean c = cards.get(card.getId());
     Response r;
     if (c != null) 
     {
        cards.put(card.getId(), card);
        r = Response.ok().build();
     } 
     else 
     {
         r = Response.notModified().build();
     }

     return r;
   }


   public Response addCard(CardBean card)
   {
     LOG.info("Ajout d'un client dont le nom est : {}", card.getName());
     card.setId(++currentId);

     cards.put(card.getId(), card);
     return Response.ok().type("application/xml").entity(card).build();
   }


   public Response deleteCard(String id)
   {
     LOG.info("Suppression d'un client dont l'identifiant est : {}", id);
     long idNumber = Long.parseLong(id);
     CardBean c = cards.get(idNumber);

     Response r;
     if (c != null) 
     {
         r = Response.ok().build();
         cards.remove(idNumber);
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