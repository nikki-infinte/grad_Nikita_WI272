SELECT current_database();


CREATE TABLE userDetails (
    uid SERIAL PRIMARY KEY,
    name VARCHAR(50),
    phone VARCHAR(15),
    role VARCHAR(10) CHECK (role IN ('ADMIN', 'OWNER'))
);


CREATE TABLE siteDetail (
    sid SERIAL PRIMARY KEY,
    uid INT,
    length INT,
    width INT,
	site_type VARCHAR(20) 
        CHECK (site_type IN ('VILLA', 'APARTMENT', 'INDEPENDENT_HOUSE', 'OPEN_SITE')),
    is_occupied BOOLEAN,
    FOREIGN KEY (uid) REFERENCES userDetails(uid)
);

Select * from userDetails;
Select * from maintenance1;
Select * from siteDetail;

CREATE TABLE maintenance1 (
    mid SERIAL PRIMARY KEY,
    sid INT,
    maintenance_year INT,
    amount DOUBLE PRECISION,
    status VARCHAR(10) CHECK (status IN ('PAID', 'PENDING')),
    payment_date DATE,
    FOREIGN KEY (sid) REFERENCES siteDetail(sid),
    UNIQUE (sid, maintenance_year)
);


