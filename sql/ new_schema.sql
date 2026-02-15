CREATE TABLE dish (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      dish_type VARCHAR(20) NOT NULL,
                      price DOUBLE
);

CREATE TABLE ingredient (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL UNIQUE,
                            price DOUBLE PRECISION NOT NULL,
                            category VARCHAR(30) NOT NULL
);

CREATE TABLE dish_ingredient (
                                 dish_id INTEGER NOT NULL,
                                 ingredient_id INTEGER NOT NULL,
                                 required_quantity DOUBLE PRECISION NOT NULL,
                                 unit VARCHAR(10) NOT NULL,

                                 PRIMARY KEY (dish_id, ingredient_id),

                                 FOREIGN KEY (dish_id) REFERENCES dish(id),
                                 FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);

select unit,
       sum(case
               when stock_movement.type = 'IN' then quantity
               when stock_movement.type = 'OUT' then  -1 * quantity
               else 0 END) as actual_quantity
from stock_movement
WHERE ingredient_id = ? and unit='KG'
  and creation_datetime <= ?
group by unit;