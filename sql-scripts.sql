create database department_db;
create database employee_db;

use department_db;
use employee_db;

select * from departments;
select * from employees;  

drop table employees;
drop table departments;

commit;