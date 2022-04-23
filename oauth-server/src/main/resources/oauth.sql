CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
   PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO oauth_client_details VALUE('CouponSystem', null, '$2a$10$dYRcFip80f0jIKGzRGulFelK12036xWQKgajanfxT65QB4htsEXNK',
    'user_info', 'authorization_code', 'http://localhost:8101/login', null, null, null, null, 'user_info');
INSERT INTO oauth_client_details VALUE('MemberSystem', null, '$2a$10$dYRcFip80f0jIKGzRGulFelK12036xWQKgajanfxT65QB4htsEXNK',
    'user_info', 'authorization_code', 'http://localhost:8101/login', null, null, null, null, 'user_info');