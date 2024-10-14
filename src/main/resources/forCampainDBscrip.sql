
CREATE TABLE IF NOT EXISTS pagination_log (
     id INTEGER NOT NULL,
    page_number INTEGER ,
   is_read_all_page BOOLEAN 
);

INSERT INTO pagination_log (id,page_number, is_read_all_page)
VALUES (1,0, false);



ALTER TABLE public.property  ALTER COLUMN country_id DROP NOT NULL;

ALTER TABLE property
ALTER COLUMN google_maps_with_google_business_places_id_screenshot_url TYPE VARCHAR,
ALTER COLUMN google_meta_search_links_url TYPE VARCHAR,
ALTER COLUMN used_booking_engine TYPE VARCHAR,
ALTER COLUMN name TYPE VARCHAR;