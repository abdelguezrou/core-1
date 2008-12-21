package org.jboss.webbeans.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.webbeans.manager.Bean;

import org.jboss.webbeans.bean.AbstractBean;
import org.jboss.webbeans.bean.EnterpriseBean;
import org.jboss.webbeans.bean.ProducerMethodBean;
import org.jboss.webbeans.bean.SimpleBean;
import org.jboss.webbeans.test.beans.Elephant;
import org.jboss.webbeans.test.beans.InitializedObserver;
import org.jboss.webbeans.test.beans.InitializedObserverWhichUsesApplicationContext;
import org.jboss.webbeans.test.beans.InitializedObserverWhichUsesRequestContext;
import org.jboss.webbeans.test.beans.LadybirdSpider;
import org.jboss.webbeans.test.beans.Panther;
import org.jboss.webbeans.test.beans.Salmon;
import org.jboss.webbeans.test.beans.SeaBass;
import org.jboss.webbeans.test.beans.Sole;
import org.jboss.webbeans.test.beans.Tarantula;
import org.jboss.webbeans.test.beans.TarantulaProducer;
import org.jboss.webbeans.test.beans.Tiger;
import org.jboss.webbeans.test.beans.Tuna;
import org.jboss.webbeans.test.ejb.valid.Hound;
import org.jboss.webbeans.test.mock.MockManagerImpl;
import org.jboss.webbeans.test.mock.MockWebBeanDiscovery;
import org.testng.annotations.Test;

public class BoostrapTest extends AbstractTest
{
   @Test(groups="bootstrap")
   public void testSingleSimpleBean()
   {
      webBeansBootstrap.boot(new MockWebBeanDiscovery(Tuna.class));
      List<Bean<?>> beans = manager.getBeans();
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      assert beans.size() == 1 + MockManagerImpl.BUILT_IN_BEANS;
      for (Bean<?> bean : beans)
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Tuna.class);
   }
   
   @Test(groups="bootstrap")
   public void testSingleEnterpriseBean()
   {
      webBeansBootstrap.boot(new MockWebBeanDiscovery(Hound.class));
      List<Bean<?>> beans = manager.getBeans();
      assert beans.size() == 1 + MockManagerImpl.BUILT_IN_BEANS;
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : beans)
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Hound.class);
   }
   
   @Test(groups="bootstrap")
   public void testMultipleSimpleBean()
   {
      webBeansBootstrap.boot(new MockWebBeanDiscovery(Tuna.class, Salmon.class, SeaBass.class, Sole.class));
      List<Bean<?>> beans = manager.getBeans();
      assert beans.size() == 4 + MockManagerImpl.BUILT_IN_BEANS;
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : beans)
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Tuna.class);
      assert classes.containsKey(Salmon.class);
      assert classes.containsKey(SeaBass.class);
      assert classes.containsKey(Sole.class);
      
      assert classes.get(Tuna.class) instanceof SimpleBean;
      assert classes.get(Salmon.class) instanceof SimpleBean;
      assert classes.get(SeaBass.class) instanceof SimpleBean;
      assert classes.get(Sole.class) instanceof SimpleBean;
   }
   
   @Test(groups="bootstrap")
   public void testProducerMethodBean()
   {
      webBeansBootstrap.boot(new MockWebBeanDiscovery(TarantulaProducer.class));
      List<Bean<?>> beans = manager.getBeans();
      assert beans.size() == 2 + MockManagerImpl.BUILT_IN_BEANS;
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : beans)
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(TarantulaProducer.class);
      assert classes.containsKey(Tarantula.class);
      
      assert classes.get(TarantulaProducer.class) instanceof SimpleBean;
      assert classes.get(Tarantula.class) instanceof ProducerMethodBean;
   }
   
   @Test(groups="bootstrap")
   public void testMultipleEnterpriseBean()
   {
      webBeansBootstrap.boot(new MockWebBeanDiscovery(Hound.class, Elephant.class, Panther.class, Tiger.class));
      List<Bean<?>> beans = manager.getBeans();
      assert beans.size() == 4 + MockManagerImpl.BUILT_IN_BEANS;
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : beans)
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Hound.class);
      assert classes.containsKey(Elephant.class);
      assert classes.containsKey(Panther.class);
      assert classes.containsKey(Tiger.class);
      
      assert classes.get(Hound.class) instanceof EnterpriseBean;
      assert classes.get(Elephant.class) instanceof EnterpriseBean;
      assert classes.get(Panther.class) instanceof EnterpriseBean;
      assert classes.get(Tiger.class) instanceof EnterpriseBean;
   }
   
   @Test(groups="bootstrap")
   public void testMultipleEnterpriseAndSimpleBean()
   {
      webBeansBootstrap.boot(new MockWebBeanDiscovery(Hound.class, Elephant.class, Panther.class, Tiger.class, Tuna.class, Salmon.class, SeaBass.class, Sole.class));
      List<Bean<?>> beans = manager.getBeans();
      assert beans.size() == 8 + MockManagerImpl.BUILT_IN_BEANS;
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : beans)
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Hound.class);
      assert classes.containsKey(Elephant.class);
      assert classes.containsKey(Panther.class);
      assert classes.containsKey(Tiger.class);
      assert classes.containsKey(Tuna.class);
      assert classes.containsKey(Salmon.class);
      assert classes.containsKey(SeaBass.class);
      assert classes.containsKey(Sole.class);
      
      assert classes.get(Hound.class) instanceof EnterpriseBean;
      assert classes.get(Elephant.class) instanceof EnterpriseBean;
      assert classes.get(Panther.class) instanceof EnterpriseBean;
      assert classes.get(Tiger.class) instanceof EnterpriseBean;
      assert classes.get(Tuna.class) instanceof SimpleBean;
      assert classes.get(Salmon.class) instanceof SimpleBean;
      assert classes.get(SeaBass.class) instanceof SimpleBean;
      assert classes.get(Sole.class) instanceof SimpleBean;
   }
   
   @Test(groups="bootstrap")
   public void testRegisterProducerMethodBean()
   {
      webBeansBootstrap.boot(new MockWebBeanDiscovery(TarantulaProducer.class));
      assert manager.getBeans().size() == 2 + MockManagerImpl.BUILT_IN_BEANS;
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : manager.getBeans())
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(TarantulaProducer.class);
      assert classes.containsKey(Tarantula.class);
      
      
      assert classes.get(TarantulaProducer.class) instanceof SimpleBean;
      assert classes.get(Tarantula.class) instanceof ProducerMethodBean;
   }
   
   @Test(groups="bootstrap")
   public void testRegisterMultipleEnterpriseAndSimpleBean()
   {
      webBeansBootstrap.boot(new MockWebBeanDiscovery(Hound.class, Elephant.class, Panther.class, Tiger.class, Tuna.class, Salmon.class, SeaBass.class, Sole.class));
      assert manager.getBeans().size() == 8 + MockManagerImpl.BUILT_IN_BEANS;
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : manager.getBeans())
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Hound.class);
      assert classes.containsKey(Elephant.class);
      assert classes.containsKey(Panther.class);
      assert classes.containsKey(Tiger.class);
      assert classes.containsKey(Tuna.class);
      assert classes.containsKey(Salmon.class);
      assert classes.containsKey(SeaBass.class);
      assert classes.containsKey(Sole.class);
      
      assert classes.get(Hound.class) instanceof EnterpriseBean;
      assert classes.get(Elephant.class) instanceof EnterpriseBean;
      assert classes.get(Panther.class) instanceof EnterpriseBean;
      assert classes.get(Tiger.class) instanceof EnterpriseBean;
      assert classes.get(Tuna.class) instanceof SimpleBean;
      assert classes.get(Salmon.class) instanceof SimpleBean;
      assert classes.get(SeaBass.class) instanceof SimpleBean;
      assert classes.get(Sole.class) instanceof SimpleBean;
   }
   
   @Test(groups="bootstrap", expectedExceptions=IllegalStateException.class)
   public void testDiscoverFails()
   {
      webBeansBootstrap.boot(null);
   }
   
   @Test(groups="bootstrap")
   public void testDiscover()
   {
      webBeansBootstrap.boot(new MockWebBeanDiscovery(new HashSet<Class<?>>(Arrays.asList(Hound.class, Elephant.class, Panther.class, Tiger.class, Tuna.class, Salmon.class, SeaBass.class, Sole.class))));
      
      assert manager.getBeans().size() == 8 + MockManagerImpl.BUILT_IN_BEANS;
      Map<Class<?>, Bean<?>> classes = new HashMap<Class<?>, Bean<?>>();
      for (Bean<?> bean : manager.getBeans())
      {
         if (bean instanceof AbstractBean)
         {
            classes.put(((AbstractBean<?, ?>) bean).getType(), bean);
         }
      }
      assert classes.containsKey(Hound.class);
      assert classes.containsKey(Elephant.class);
      assert classes.containsKey(Panther.class);
      assert classes.containsKey(Tiger.class);
      assert classes.containsKey(Tuna.class);
      assert classes.containsKey(Salmon.class);
      assert classes.containsKey(SeaBass.class);
      assert classes.containsKey(Sole.class);
      
      assert classes.get(Hound.class) instanceof EnterpriseBean;
      assert classes.get(Elephant.class) instanceof EnterpriseBean;
      assert classes.get(Panther.class) instanceof EnterpriseBean;
      assert classes.get(Tiger.class) instanceof EnterpriseBean;
      assert classes.get(Tuna.class) instanceof SimpleBean;
      assert classes.get(Salmon.class) instanceof SimpleBean;
      assert classes.get(SeaBass.class) instanceof SimpleBean;
      assert classes.get(Sole.class) instanceof SimpleBean;
   }
   
   @Test(groups="bootstrap")
   public void testInitializedEvent()
   {
      assert !InitializedObserver.observered;
      webBeansBootstrap.boot(new MockWebBeanDiscovery(new HashSet<Class<?>>(Arrays.asList(InitializedObserver.class))));
      
      assert InitializedObserver.observered;
   }
   
   @Test(groups="bootstrap")
   public void testRequestContextActiveDuringInitializtionEvent()
   {
      webBeansBootstrap.boot(new MockWebBeanDiscovery(new HashSet<Class<?>>(Arrays.asList(InitializedObserverWhichUsesRequestContext.class, Tuna.class)), null, new HashSet<Class<?>>()));
   }
   
   @Test(groups="bootstrap")
   public void testApplicationContextActiveDuringInitializtionEvent()
   {
      webBeansBootstrap.boot(new MockWebBeanDiscovery(new HashSet<Class<?>>(Arrays.asList(InitializedObserverWhichUsesApplicationContext.class, LadybirdSpider.class)), null, new HashSet<Class<?>>()));
   }
   
}
