package org.example;


import java.util.Optional;
import java.util.function.Function;

/**
 * hasell monad laws
 * return a >>= k	=	k a
 * m >>= return	=	m
 * xs >>= return . f	=	fmap f xs
 * m >>= (\x -> k x >>= h)	=	(m >>= k) >>= h
 */
public class MonadLaws {
    //return a >>= k	=	k a

    public  <A> boolean law1(A a, Function<A, Optional<A>> k) {
         return Optional.ofNullable(a).flatMap(k).equals(k.apply(a));
    }

    public  <A> boolean law2(Optional<A> m) {
        return m.flatMap(Optional::ofNullable).equals(m);
    }

    public  <A> boolean law3(Optional<A> xs, Function<A,A> f) {
        return xs.flatMap( x ->  Optional.ofNullable(f.apply(x))).equals(xs.map(f));
    }

    public void test() {
        System.out.println("Left identity: " );
        System.out.println(law1(1, x -> Optional.of(x + 1)));
        System.out.println(law2(Optional.of(7)));
        System.out.println(law3(Optional.of(7), x -> x + 1));

        Long y = null;

        System.out.println(law1(y, x -> {
            if (x == null) {
                return Optional.of(2L);
            } else {
                return Optional.empty();
            }
        }));
        System.out.println(law2(Optional.ofNullable(y)));
        System.out.println(law3(Optional.ofNullable(null), x -> null));


    }
}
