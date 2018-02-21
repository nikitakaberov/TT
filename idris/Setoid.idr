module Setoid

%access public export
%default total

Reflx : {A : Type} -> (R : A -> A -> Type) -> Type
Reflx {A} R = (x : A) -> R x x

Sym : {A : Type} -> (R : A -> A -> Type) -> Type
Sym {A} R = (x : A) -> (y : A) -> R x y -> R y x

Trans : {A : Type} -> (R : A -> A -> Type) -> Type
Trans {A} R = (x : A) -> (y : A) -> (z : A) -> R x y -> R y z -> R x z

data IsEquivalence : {A : Type} -> (R : A -> A -> Type) -> Type where
    EqProof : {A : Type} -> (R : A -> A -> Type) -> Reflx {A} R -> Sym {A} R -> Trans {A} R -> IsEquivalence {A} R

record Setoid where
    constructor MkSetoid
    Carrier : Type
    Equiv : Carrier -> Carrier -> Type
    EquivProof : IsEquivalence Equiv

intensional_setoid : Type -> Setoid
intensional_setoid t = MkSetoid t (=) (e {Carrier=t})
  where
    refl_eq : {Carrier : Type} -> Reflx {A=Carrier} (=)
    refl_eq x = Refl {x=x}

    sym_eq : {Carrier : Type} -> Sym {A = Carrier} (=)
    sym_eq x y = sym

    trans_eq : {Carrier : Type} -> Trans {A = Carrier} (=)
    trans_eq a b c = trans

    e : {Carrier : Type} -> IsEquivalence {A=Carrier} (=)
    e {Carrier} = EqProof {A=Carrier} (=) refl_eq sym_eq trans_eq