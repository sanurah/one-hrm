INSERT INTO public.users(created_on, updated_on, email, password, role, verified)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'sanurah@hrm.com',
        '$2a$10$Xs8Jbzpy3bHlmUSK.Di79.WJlSFnNnuJOR1p8EkTL0s82S1XXQgCa', 'ADMIN', true)
ON CONFLICT DO NOTHING;