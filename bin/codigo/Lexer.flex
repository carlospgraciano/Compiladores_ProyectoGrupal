package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
D = [0-9]+
O = [\-+/*\^]
%{
	public String lexeme;
%}
%%
"+" {return Suma;}
"-" {return Resta;}
"*" {return Multiplicacion;}
"/" {return Division;}
"^" {return Potencia;}
"(" {return Abre_Parentesis;}
")" {return Cierra_Parentesis;}
{D} {lexeme=yytext(); return Digito;}
^[(]?[\-]?({D})[)]??([(]?({O}({D}))?({D})?[)]?)* {lexeme=yytext(); return Valida;}
. {return ERROR;}