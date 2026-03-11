CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE organizations (
   id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   name VARCHAR(255) NOT NULL,
   plan_type VARCHAR(255) DEFAULT 'FREE',
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE users (
   id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
   organization_id UUID NOT NULL,
   name VARCHAR(255),
   email VARCHAR(255) UNIQUE NOT NULL,
   password_hash TEXT NOT NULL,
   role VARCHAR(50) DEFAULT 'MEMBER',
   is_verified BOOLEAN DEFAULT 'FALSE',
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

   CONSTRAINT fk_user_org
   FOREIGN KEY (organization_id)
       REFERENCES organizations(id)
       ON DELETE CASCADE
   );

CREATE TABLE domains (
       id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
       organization_id UUID NOT NULL,
       domain_name VARCHAR(255) UNIQUE NOT NULL,
       is_verified BOOLEAN DEFAULT FALSE,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

       CONSTRAINT fk_domain_org
       FOREIGN KEY (organization_id)
       REFERENCES organizations(id)
       ON DELETE CASCADE
   );

   CREATE TABLE links (
       id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
       organization_id UUID NOT NULL,
       domain_id UUID,
       created_by UUID,
       original_url TEXT NOT NULL,
       short_url VARCHAR(500) UNIQUE NOT NULL,
       title VARCHAR(255),
       is_active BOOLEAN DEFAULT TRUE,
       expires_at TIMESTAMP,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

       CONSTRAINT fk_link_org
       FOREIGN KEY (organization_id)
       REFERENCES organizations(id),

       CONSTRAINT fk_link_domain
       FOREIGN KEY (domain_id)
       REFERENCES domains(id),

       CONSTRAINT fk_link_user
       FOREIGN KEY (created_by)
       REFERENCES users(id)
   );

   CREATE TABLE link_clicks (
       id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
       link_id UUID NOT NULL,
       ip_address VARCHAR(45),
       country VARCHAR(100),
       city VARCHAR(100),
       device_type VARCHAR(50),
       browser VARCHAR(100),
       referrer TEXT,
       clicked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

       CONSTRAINT fk_click_link
       FOREIGN KEY (link_id)
       REFERENCES links(id)
       ON DELETE CASCADE
   );

   CREATE TABLE api_keys (
       id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
       organization_id UUID NOT NULL,
       api_key VARCHAR(255) UNIQUE NOT NULL,
       name VARCHAR(255),
       is_active BOOLEAN DEFAULT TRUE,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

       CONSTRAINT fk_api_org
       FOREIGN KEY (organization_id)
       REFERENCES organizations(id)
   );

   CREATE TABLE subscriptions (
       id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
       organization_id UUID UNIQUE NOT NULL,
       plan_name VARCHAR(50),
       status VARCHAR(50),
       start_date TIMESTAMP,
       end_date TIMESTAMP,

       CONSTRAINT fk_subscription_org
       FOREIGN KEY (organization_id)
       REFERENCES organizations(id)
   );