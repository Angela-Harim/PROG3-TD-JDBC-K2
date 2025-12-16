CREATE DATABASE mini_dish_db;

CREATE USER mini_dish_db_manager;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO mini_dish_db_manager;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO mini_dish_db_manager;
GRANT INSERT, SELECT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO mini_dish_db_manager;



