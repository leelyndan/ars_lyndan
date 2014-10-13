


DROP TABLE IF EXISTS flight;

create table flight(
		id integer primary key auto_increment,
		flight varchar(120),
		departAirport varchar(30),
		arrivalAirport varchar(30),
		departTime varchar(10),
		arrivalTime varchar(10),
		firstPrice varchar(100),
		businessPrice varchar(100),
		economicPrice varchar(100)
);

DROP TABLE IF EXISTS ticket;
	
create table ticket(
		id integer primary key auto_increment,
		flight varchar(20),
		departAirport varchar(30),
		arrivalAirport varchar(30),
		departDate varchar(20),
		departTime varchar(10),
		adultNum integer,
		childNum integer,
		seatClass varchar(50),
		totalMoney varchar(100),
		promotionMoney varchar(100),
		payment varchar(100),
		changes varchar(100),
		reserveNumber varchar(100)
		
);
insert into flight(flight,departAirport,arrivalAirport,departTime,arrivalTime,firstPrice,businessPrice,economicPrice) values("A100","Xi'an","Incheon","10:00","14:00","4000","3000","2000");
insert into flight(flight,departAirport,arrivalAirport,departTime,arrivalTime,firstPrice,businessPrice,economicPrice) values("A101","Xi'an","Incheon","15:00","19:00","4000","3000","2000");
insert into flight(flight,departAirport,arrivalAirport,departTime,arrivalTime,firstPrice,businessPrice,economicPrice) values("A102","Xi'an","Incheon","20:00","24:00","4000","3000","2000");
insert into flight(flight,departAirport,arrivalAirport,departTime,arrivalTime,firstPrice,businessPrice,economicPrice) values("A100","Incheon","Xi'an","18:00","20:30","3000","2000","1500");
insert into flight(flight,departAirport,arrivalAirport,departTime,arrivalTime,firstPrice,businessPrice,economicPrice) values("A101","Incheon","Xi'an","09:00","11:30","3000","2000","1500");
insert into flight(flight,departAirport,arrivalAirport,departTime,arrivalTime,firstPrice,businessPrice,economicPrice) values("A102","Incheon","Xi'an","12:00","14:30","3000","2000","1500");




	
	