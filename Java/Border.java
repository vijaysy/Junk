JPanel pan = new JPanel();


TitledBorder title=BorderFactory.createTitledBorder("Register");
title.setTitleColor(Color.BLACK);
Border rsi=BorderFactory.createRaisedBevelBorder();
Border comp1=BorderFactory.createCompoundBorder(rsi,title);
  pan.setBorder(comp1);
  pan.setBackground(Color.white);
  pan.setBounds(670, 100+40,325,400);
  //Should add after adding all components...............................i.e this.add(pan);