{
		K.clear();
		int i1 = 0;
        for(i1 = 0; i1 < d.size(); i1++)
        {
            gq gq1 = (gq)d.get(i1);
            int j1 = ic.b(gq1.p / 16D);
            int l1 = ic.b(gq1.r / 16D);
            byte byte0 = 9;
            for(int j2 = -byte0; j2 <= byte0; j2++)
            {
                for(int i3 = -byte0; i3 <= byte0; i3++)
                {
                    K.add(new lk(j2 + j1, i3 + l1));
                }

            }

        }

        if(L > 0)
        {
            L--;
        }
        for(Iterator iterator = K.iterator(); iterator.hasNext();)
        {
            lk lk1 = (lk)iterator.next();
            int k1 = lk1.a * 16;
            int i2 = lk1.b * 16;
            kx kx1 = c(lk1.a, lk1.b);
            if(L == 0)
            {
                g = g * 3 + h;
                int k2 = g >> 2;
                int j3 = k2 & 0xf;
                int l3 = k2 >> 8 & 0xf;
                int j4 = k2 >> 16 & 0x7f;
                int l4 = kx1.a(j3, j4, l3);
                j3 += k1;
                l3 += i2;
                if(l4 == 0 && i(j3, j4, l3) <= l.nextInt(8) && a(eb.a, j3, j4, l3) <= 0)
                {
                    gq gq2 = a((double)j3 + 0.5D, (double)j4 + 0.5D, (double)l3 + 0.5D, 8D);
                    if(gq2 != null && gq2.d((double)j3 + 0.5D, (double)j4 + 0.5D, (double)l3 + 0.5D) > 4D)
                    {
                        a((double)j3 + 0.5D, (double)j4 + 0.5D, (double)l3 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + l.nextFloat() * 0.2F);
                        L = l.nextInt(12000) + 6000;
                    }
                }
            }
            int l2 = 0;
            while(l2 < 80) 
            {
                g = g * 3 + h;
                int k3 = g >> 2;
                int i4 = k3 & 0xf;
                int k4 = k3 >> 8 & 0xf;
                int i5 = k3 >> 16 & 0x7f;
                byte byte1 = kx1.b[i4 << 11 | k4 << 7 | i5];
                if(gv.n[byte1])
                {
					{MIDDLECRAFT_onBlockTick(i4 + k1, i5, k4 + i2);}
                    gv.m[byte1].a(this, i4 + k1, i5, k4 + i2, l);
                }
                l2++;
            }
        }
}