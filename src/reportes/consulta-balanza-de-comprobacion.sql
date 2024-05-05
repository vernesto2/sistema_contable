/*Vista para simplificar las consultas que recuperan los saldos de las cuentas*/

create view vw_cargo_abono as 
select 
	0 as id_partida,
	cb.id_cuenta, 
	case tipo_saldo
		when 'D' then saldo_inicial
		when 'A' then 0
		else 0 end 
	as debe, 
	case tipo_saldo
		when 'D' then 0
		when 'A' then saldo_inicial
		else 0 end 
	as haber, 
	0 as parcial, 
    0 as eliminado
	from cuenta_balance cb 
	inner join cuenta c on cb.id_cuenta = c.id
UNION
select 
	pdi.id_partida, pdi.id_cuenta, pdi.debe, pdi.haber, pdi.parcial, pdi.eliminado
from partida_detalle pdi


select * from vw_cargo_abono;

with cte_balanza_comprobacion as (
  select c.id, ccf.folio_mayor, c.codigo, c.nombre, c.tipo_saldo, 
  case 
    when tipo_saldo = 'D' then debe - haber
    when tipo_saldo = 'A' then haber - debe
    else 0 
  end
  as saldo_inicial, 
  row_number() over (PARTITION by c.id order by p.fecha || ' ' || p.hora asc, p.id asc) as row_number
  from cuenta c 
  inner join vw_cargo_abono pd on pd.id_cuenta = c.id and pd.parcial = 0
  left join partida p on pd.id_partida = p.id
  left join ciclo_contable_folios ccf on ccf.id_ciclo_contable = p.id_ciclo
  where c.eliminado = false 
  and p.eliminado = false
  and pd.eliminado = false
  and p.id_ciclo = 21
  and c.id_tipo_catalogo = 1
  and (
      null is null or c.id = null
  )
)
select cbc.*, saldo_calculado.saldo_deudor, saldo_calculado.saldo_acreedor
from cte_balanza_comprobacion cbc 
inner join (
  select c.id, 
  sum(
      case
        when tipo_saldo = 'D' then debe - haber
	else 0
    end
  ) as saldo_deudor, 
  sum(
      case
        when tipo_saldo = 'A' then haber - debe
	else 0
      end
  ) as saldo_acreedor
  from cuenta c 
    inner join vw_cargo_abono pd on pd.id_cuenta = c.id
    left join partida p on pd.id_partida = p.id
    where c.eliminado = false 
    and p.eliminado = false
    and pd.eliminado = false
    and p.id_ciclo = 21
    and c.id_tipo_catalogo = 1
	and p.id_tipo_partida <= 1
	and (
      null is null or c.id = null
    )
  group by c.id, c.codigo, c.nombre, c.tipo_saldo
) as saldo_calculado on saldo_calculado.id = cbc.id
where row_number = 1
order by folio_mayor
