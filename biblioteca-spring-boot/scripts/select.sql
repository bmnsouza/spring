--Editora
select * from public.editora;
select * from public.editora edi
 where (cast(?1 as varchar) is null or edi.codigo = to_number(cast(?1 as varchar), '999999999')) 
   and (cast(?2 as varchar) is null or upper(edi.nome) like upper('%' || cast(?2 as varchar) || '%')) order by edi.nome;


--Local
select * from public.local loc
 where (cast(?1 as varchar) is null or loc.codigo = to_number(cast(?1 as varchar), '999999999')) 
   and (cast(?2 as varchar) is null or upper(loc.nome) like upper('%' || cast(?2 as varchar) || '%')) order by loc.nome;


--Autor
select * from public.autor;
select * from public.autor aut
 where (cast(?1 as varchar) is null or aut.codigo = to_number(cast(?1 as varchar), '999999999')) 
   and (cast(?2 as varchar) is null or upper(aut.primeiro_nome) like upper('%' || cast(?2 as varchar) || '%')) 
   and (cast(?3 as varchar) is null or upper(aut.inicial_meio_nome) = upper(cast(?3 as varchar))) 
   and (cast(?4 as varchar) is null or upper(aut.ultimo_nome) like upper('%' || cast(?4 as varchar) || '%')) order by aut.primeiro_nome;