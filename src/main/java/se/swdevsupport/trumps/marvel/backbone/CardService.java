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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
public interface CardService 
{
    /**
     * Map method to request HTTP GET : "http://localhost:8181/cxf/marvel/cards/{id}". 
     * where {id} is passed as a param to the method using annotation @PathParam.
     * <p/>
     * Method returns an object of class CardBean on HTTP response. The object is transformed to XML from JAXB.
     * <p/>
     * Example URL "http://localhost:8181/cxf/marvel/cards/1" gets card with id 1 in XML.
     */
     @GET
     @Path("/cards/{id}/")
     @Produces("application/xml")
     public CardBean getCard(@PathParam("id") String id);

     @GET
     @Path("/cards/")
     @Produces("application/xml")
     public CardBean getCards();

     
   /**
    * Cette méthode est mappée à une requête HTTP PUT. On peut ainsi envoyer la représentation XML d'un objet cardBean.
     * La représentation XML sera obtenue par transformation d'un CardBean par JAXB.
   * <p/>
     * Cette méthode met à jour un objet CardBean dans notre map locale puis utilise la classe Response pour construire 
   * une réponse HP appropriée : soit OK si la mise à jour a été effectuée avec succès (Traduction du statut HTTP 200/OK) 
   * ou NON MODIFIE si la mise à jour de l'objet CardBean a échoué (Traduction du statut HTTP 304/Not Modified).  
   * <p/>
   * À NOTER : cette méthode utilise la même valeur de @path que la méthode suivante. La méthode HTTP utilisée déterminera 
   * quelle sera la méthode à invoquer
   * 
   */
   @PUT
   @Path("/cards/")
   @Consumes({"application/xml", "application/json" })
   public Response updateCard(CardBean card);



  /**
   * Utilisation de la requête HTTP POST permettant d'ajouter un nouveau customer au système en uploadant la représentation XML 
   * d'un objet CardBean.
   * Cette opération sera mappée à la méthode ci-dessous et la représentation XML sera transformée en un objet CardBean.
   * <p/>
   * Après que cette méthode aura ajouté le client dans la map local, elle utilisera la classe Response pour construire la réponse HTTP 
   * en retournant à la fois l'objet CardBean inséré et le statut HTTP 200/OK. Ceci permet de récupérer l'ID du nouvel objet CardBean. 
   *<p/>
   * À NOTER : cette méthode utilise la même valeur de @path que la méthode précédente. La méthode HTTP utilisée déterminera 
    * quelle sera la méthode à invoquer
   */
   @POST
   @Path("/cards/")
   @Consumes({"application/xml", "application/json" })
   public Response addCard(CardBean cards);


 
   /**
    * Cette méthode est mappée à une requête HTTP DELETE du type : "http://localhost:8181/cxf/olivier/customers/{id}".
   * La valeur pour {id} sera passée en tant que paramètre en utilisant l'annotation @PathParam.
   * <p/>
   * Cette méthode utilise la classe Response pour créer une réponse HTTP : soit le statut HTTP 200/OK si l'objet CardBean 
   * a été correctement supprimé de la map locale, soit le statut HTTP 304/Not Modified si la suppression a échoué.
   */
   @DELETE
   @Path("/card/{id}/")
   public Response deleteCard(@PathParam("id") String id);


  /**
    * Cette méthode est mappée à une requête HTTP GET du type : "http://localhost:8181/cxf/olivier/orders/{id}".
   * La valeur pour {id} sera passée en tant que paramètre en utilisant l'annotation @PathParam.
   * <p/>
   * La méthode retournera un objet de la classe Product par création d'une réponse HTTP. Cet objet sera transformé en XML par JAXB.
    * <p/>
   * Par exemple, l'appel de l'URL "http://localhost:8181/cxf/olivier/orders/223" provoquera l'affichage du customer 123 au format XML.
   */
   @GET
   @Path("/orders/{orderId}")
    @Produces("application/xml")
   public OrderBean getOrder(@PathParam("orderId") String orderId);
  

  /**
   * Cette méthode est mappée à une requête HTTP GET du type : "http://localhost:8181/cxf/olivier/orders/{id}/products/{productId}".
   * La valeur pour {id} et {productId} sera passée en tant que paramètre en utilisant l'annotation @PathParam.
   * <p/>
   * La méthode retournera un objet de la classe Product par création d'une réponse HTTP. Cet objet sera transformé en XML par JAXB.
   * <p/>
   * Par exemple, l'appel de l'URL "http://localhost:8181/cxf/olivier/orders/223/products/323/" provoquera l'affichage du customer 123 au format XML.
   */
   @GET
   @Path("/orders/{orderId}/products/{productId}")
    @Produces("application/xml")
   public Product getProduct(@PathParam("orderId") String orderId,@PathParam("productId") int productId);

 
  /**
   * Cette méthode est mappée à une requête HTTP GET du type : "http://localhost:8181/cxf/olivier/orders/{id}/products".
   * La valeur pour {id} sera passée en tant que paramètre en utilisant l'annotation @PathParam.
   * <p/>
   * La méthode retournera un objet de la classe Product par création d'une réponse HTTP. Cet objet sera transformé en XML par JAXB.
   * <p/>
   * Par exemple, l'appel de l'URL "http://localhost:8181/cxf/olivier/orders/223/products/323/" provoquera l'affichage du customer 123 au format XML.
   */
   @GET
   @Path("/orders/{orderId}/products")
     @Produces("application/xml")
   public Products getProducts(@PathParam("orderId") String orderId);
  
}
