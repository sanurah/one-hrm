INSERT INTO public.users(created_on, updated_on, email, password, role, verified)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'sanurah@hrm.com', 'password', 'ADMIN', true)
ON CONFLICT DO NOTHING;