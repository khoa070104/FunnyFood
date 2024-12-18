CREATE DATABASE FunnyFood;

USE FunnyFood;

CREATE TABLE users (
    id INT AUTO_INCREMENT,
    username VARCHAR(100),
    password VARCHAR(100),
    fullname VARCHAR(50),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    roleid INT,
    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id INT AUTO_INCREMENT,
    rolename VARCHAR(10),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE categories (
    id INT AUTO_INCREMENT,
    namecate VARCHAR(50),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE foods (
    id INT AUTO_INCREMENT,
    title VARCHAR(255),
    image TEXT,
    timeship VARCHAR(10),
    price DECIMAL(10, 2),
    cateid INT,
    PRIMARY KEY (id)
);

CREATE TABLE RatingFood (
    id INT AUTO_INCREMENT,
    userid INT,
    foodid INT,
    content TEXT,
    rate_point INT,
    PRIMARY KEY (id)
);

CREATE TABLE restaurant (
    id INT AUTO_INCREMENT,
    title VARCHAR(255),
    subtitle VARCHAR(255),
    descript TEXT,
    image TEXT,
    isfreeship BOOLEAN,
    address VARCHAR(255),
    opendate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE MenuRestaurant (
    cateid INT,
    resid INT,
    createdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (cateid, resid)
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT,
    userid INT,
    resid INT,
    createdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    price DECIMAL(10, 2),
    PRIMARY KEY (id)
);

CREATE TABLE orderDetail (
    orderid INT,
    foodid INT,
    createdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    price DECIMAL(10, 2),
    PRIMARY KEY (orderid, foodid)
);

CREATE TABLE RatingRestaurant (
    id INT AUTO_INCREMENT,
    resid INT,
    content TEXT,
    ratepoint INT,
    PRIMARY KEY (id)
);

CREATE TABLE promo (
    id INT AUTO_INCREMENT,
    resid INT,
    percent DOUBLE,
    startdate TIMESTAMP,
    enddate TIMESTAMP,
    PRIMARY KEY (id)
);

-- Thêm các ràng buộc
ALTER TABLE users ADD CONSTRAINT fk_users_role_id FOREIGN KEY (roleid) REFERENCES roles (id);
ALTER TABLE foods ADD CONSTRAINT fk_foods_cate_id FOREIGN KEY (cateid) REFERENCES categories (id);
ALTER TABLE RatingFood ADD CONSTRAINT fk_ratingfood_user_id FOREIGN KEY (userid) REFERENCES users (id);
ALTER TABLE RatingFood ADD CONSTRAINT fk_ratingfood_food_id FOREIGN KEY (foodid) REFERENCES foods (id);
ALTER TABLE MenuRestaurant ADD CONSTRAINT fk_MenuRestaurant_cate_id FOREIGN KEY (cateid) REFERENCES categories (id);
ALTER TABLE MenuRestaurant ADD CONSTRAINT fk_MenuRestaurant_res_id FOREIGN KEY (resid) REFERENCES restaurant (id);
ALTER TABLE orders ADD CONSTRAINT fk_orders_user_id FOREIGN KEY (userid) REFERENCES users (id);
ALTER TABLE orders ADD CONSTRAINT fk_orders_res_id FOREIGN KEY (resid) REFERENCES restaurant (id);
ALTER TABLE orderDetail ADD CONSTRAINT fk_orderdetail_order_id FOREIGN KEY (orderid) REFERENCES orders (id);
ALTER TABLE orderDetail ADD CONSTRAINT fk_orderdetail_food_id FOREIGN KEY (foodid) REFERENCES foods (id);
ALTER TABLE RatingRestaurant ADD CONSTRAINT fk_ratingrestaurant_res_id FOREIGN KEY (resid) REFERENCES restaurant (id);
ALTER TABLE promo ADD CONSTRAINT fk_promo_res_id FOREIGN KEY (resid) REFERENCES restaurant (id);
