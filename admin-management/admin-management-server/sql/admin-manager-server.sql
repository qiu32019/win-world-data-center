CREATE TABLE IF NOT EXISTS site_info (
    id INT AUTO_INCREMENT,
    siteNo VARCHAR(10),
	sportSiteNo VARCHAR(10) NOT NULL,
    uppername varchar(255) not null,
	deskey varchar(255),
	allowedtype varchar(255),
	perbatch INT(11) default 1000,
	prefix varchar(10),
	disabled TINYINT default 0,
	ptscore INT(11),
    PRIMARY KEY (id)
)  ENGINE=INNODB;

insert into site_info(siteNo, sportSiteNo, uppername, deskey, allowedtype, perbatch, prefix, disabled, ptscore)
values(#{siteNo}, #{sportSiteNo}, #{uppername}, #{deskey}, #{allowedtype}, #{perbatch}, #{prefix},#{disabled}, #{ptscore})