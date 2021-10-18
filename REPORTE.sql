ALTER SESSION SET nls_date_format = 'YYYY-MM-DD';
-- EJEMPLO
  BEGIN
      EXECUTE IMMEDIATE 'DROP TABLE libros';
  EXCEPTION
      WHEN OTHERS THEN NULL;
  END;
  /

 create table libros(
  titulo varchar2(40),
  autor varchar2(30),
  editorial varchar2(20),
  precio number(5,2),
  fecha date
 );

 insert into libros values ('Uno','Richard Bach','Planeta',15,'2021-10-18');
 insert into libros values ('Ilusiones','Richard Bach','Planeta',12,'2021-10-17');
 insert into libros values ('El aleph','Borges','Emece',25,'2021-10-16');
 insert into libros values ('Aprenda PHP','Mario Molina','Nuevo siglo',50,'2021-10-15');
 insert into libros values ('Matematica estas ahi','Paenza','Nuevo siglo',18,'2021-10-14');
 insert into libros values ('Puente al infinito','Bach Richard','Sudamericana',14,'2021-10-13');
 insert into libros values ('Antología','J. L. Borges','Paidos',24,'2021-10-12');
 insert into libros values ('Java en 10 minutos','Mario Molina','Siglo XXI',45,'2021-10-11');
 insert into libros values ('Cervantes y el quijote','Borges- Casares','Planeta',34,'2021-10-10');
 
 
 select*from libros where fecha='2021-10-11';
 
CREATE OR REPLACE VIEW v_fechaLibro AS
select distinct fecha from libros order by fecha desc;

select TO_CHAR( fecha, 'dd/MM/YY' )as fecha from v_fechaLibro;
 
 