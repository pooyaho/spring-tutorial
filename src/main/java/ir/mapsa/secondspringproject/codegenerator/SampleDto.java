package ir.mapsa.secondspringproject.codegenerator;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class SampleDto {
    private String name;
    private String family;
    private Long id;
    private String nationalCode;

    public static void main(String[] args) throws JsonProcessingException {
        C c = new C();
        D d = new D();
        c.setD(d);
        d.setC(c);

        System.out.println(new ObjectMapper().writeValueAsString(c));
    }

    public static void main2(String[] args) {
        A a = new A();
        A o = (A) Enhancer.create(A.class, (MethodInterceptor) (obj, method, args12, proxy) -> {
            System.out.println("In proxy");
            return method.invoke(a);
        });
        B b = new B();
        b.setA(o);
        B o1 = (B) Enhancer.create(B.class, (MethodInterceptor) (obj, method, args1, proxy) -> {
            System.out.println("In proxy");
            return method.invoke(b);
        });
        o1.method2();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

}

class C {
    private D d;
    private String name = "C";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonManagedReference
    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }
}

class D {
    private C c;
    private String name = "D";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonBackReference
    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }
}

class A {
    public void method1() {
        System.out.println("Method1 in parent");
    }


}

class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public void method2() {
        a.method1();
    }
}
