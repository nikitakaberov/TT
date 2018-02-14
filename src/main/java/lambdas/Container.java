package lambdas;

public interface Container {

    Lambda getLambda();

    Container reduceLambda();

    Container sub(Var var, Container subst);

    Container sub(Var varSubst,
                  Container subst,
                  VStack oldStack,
                  VStack newStack);

    Container comp();
}
