module MyInt

import Setoid

%access public export
%default total

data MyInt = Sub Nat Nat

implementation Num MyInt where
    (+) (Sub x1 y1) (Sub x2 y2) = Sub (x1 + x2) (y1 + y2)
    (*) (Sub x1 y1) (Sub x2 y2) = Sub (x1 * x2 + y1 * y2) (x1 * y2 + y1 * x2)

    fromInteger x = if x < 0 then Sub Z (fromInteger (abs x)) else Sub (fromInteger x) Z

implementation Neg MyInt where
    negate (Sub x y)            = Sub y x
    (-) (Sub x1 y1) (Sub x2 y2) = Sub (x1 + y2) (y1 + x2)

multNat : MyInt -> Nat -> MyInt
multNat (Sub x y) k = Sub (x * k) (y * k)

data IntEq : MyInt -> MyInt -> Type where
    IntRefl : (eq : a + d = c + b) -> IntEq (Sub a b) (Sub c d)

intRefl : Reflx IntEq
intRefl (Sub a b) = IntRefl $ Refl {x = a + b}

intSym : Sym IntEq
intSym (Sub _ _) (Sub _ _) (IntRefl eq) = IntRefl (sym eq)

reflPlusRefl : {a : Nat} -> {b : Nat} -> {c : Nat} -> {d : Nat} ->
           (a = b) -> (c = d) -> (a + c = b + d)
reflPlusRefl eq1 eq2 = rewrite eq1 in rewrite eq2 in Refl

intTrans : Trans IntEq
intTrans (Sub a b) (Sub c d) (Sub e f) (IntRefl eq1) (IntRefl eq2) = IntRefl rev2
  where
    eq3  : (a + d) + (c + f) = (c + b) + (e + d)
    eq3  = reflPlusRefl eq1 eq2

    elD1 : (a + d) + (c + f) = (c + f) + (a + d)
    elD1 = plusCommutative (a + d) (c + f)

    elD2 : (c + f) + (a + d) = ((c + f) + a) + d
    elD2 = plusAssociative (c + f) a d

    elD3 : (c + b) + (e + d) = ((c + b) + e) + d
    elD3 = plusAssociative (c + b) e d

    elD4 : ((c + f) + a) + d = ((c + b) + e) + d
    elD4 = trans (sym elD2) $ trans (sym elD1) $ trans eq3 elD3

    elD  : (c + f) + a = (c + b) + e
    elD  = plusRightCancel ((c + f) + a) ((c + b) + e) d elD4

    elC1 : c + (f + a) = (c + b) + e
    elC1 = trans (plusAssociative c f a) elD

    elC2 : (f + a) + c = (c + b) + e
    elC2 = trans (plusCommutative (f + a) c) elC1

    elC3 : (f + a) + c = c + (b + e)
    elC3 = trans elC2 $ sym $ plusAssociative c b e

    elC4 : (f + a) + c = (b + e) + c
    elC4 = trans elC3 (plusCommutative c (b + e))

    elC  : f + a = b + e
    elC  = plusRightCancel (f + a) (b + e) c elC4

    rev1 : a + f = b + e
    rev1 = trans (plusCommutative a f) elC

    rev2 : a + f = e + b
    rev2 = trans rev1 (plusCommutative b e)

MyIntSetoid : Setoid
MyIntSetoid = MkSetoid MyInt IntEq $ EqProof IntEq intRefl intSym intTrans
