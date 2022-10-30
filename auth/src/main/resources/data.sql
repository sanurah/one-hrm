INSERT INTO public.users(created_on, updated_on, email, password, role, verified)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'sanurah@hrm.com',
        '$2a$10$Uqhv0XGXPfUq4fVjuWRAJ.c4ShxYukzhIMVPqHyNZZBKVwscleUzm', 'ADMIN', true)
ON CONFLICT DO NOTHING;