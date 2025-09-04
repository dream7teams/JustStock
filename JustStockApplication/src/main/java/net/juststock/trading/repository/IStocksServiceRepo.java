package net.juststock.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.juststock.trading.domain.Stock;


@Repository
public interface IStocksServiceRepo  extends JpaRepository<Stock,Long>{

}
