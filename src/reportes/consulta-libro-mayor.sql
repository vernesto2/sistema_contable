select mayorizacion_cuenta.*, 
sum( 
	case 
	  when tipo_saldo = 'D' then debe - haber
	  when tipo_saldo = 'A' then haber - debe
	  else 0
	end 
) over ( order by num_partida asc ) as saldo
from (
		select 
		0 as id, 
		tipo_saldo, 
		null as fecha, 
		null as id_tipo_partida,
		"#SALDO ASIGNADO MANUALMENTE#" as comentario, 
		0 as num_partida, 
		case tipo_saldo
			when 'D' then saldo_inicial
			when 'A' then 0
			else 0 end 
		as debe, 
		case tipo_saldo
			when  'D' then 0
			when 'A' then saldo_inicial
			else 0 end 
		as haber
		from cuenta_balance cb 
		inner join cuenta c on cb.id_cuenta = c.id
		where 
		c.eliminado = false
	union
		select 
		  p.id, 
		  tipo_saldo, 
		  fecha, 
		  p.id_tipo_partida, 
		  comentario, 
		  p.num_partida, 
		  pd.debe, 
		  pd.haber
		from (
		  select pi.*, 
		  row_number() over ( order by pi.fecha ||  ' ' || pi.hora asc) as num_partida
		  from partida pi
		  where pi.id_ciclo = 21
		) p
		inner join partida_detalle pd on pd.id_partida = p.id
		inner join cuenta c on pd.id_cuenta = c.id
		where 
		c.eliminado = false
		and pd.id_cuenta =  596
		and p.id_ciclo =  21
		and p.eliminado = false 
		and pd.eliminado = false
		and (
		  p.id_tipo_partida <= 1
		)
) mayorizacion_cuenta


