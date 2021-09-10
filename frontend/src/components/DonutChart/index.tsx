import { useState, useEffect } from 'react';
import axios from 'axios';
import Chart from "react-apexcharts";
import { BASE_URL } from 'utils/requests';
import { SaleSum } from 'types/sale';

type ChartData = {
  labels: string[];
  series: number[];
}

function DonutChart() {
  const [chartData, setChartData] = useState<ChartData>({ labels: [], series: [] });

  useEffect(() => { 
    axios.get(`${BASE_URL}/sales/amount-by-seller`)
      .then(({ data }) => {
        const result = data as SaleSum[];
        
        const labels = result.map(r => r.sellerName);
        const series = result.map(r => r.sum);
        
        setChartData({ labels, series });
      })
      .catch(() => setChartData({ labels: [], series: [] })) 
  }, []);

  const options = {
    legend: {
      show: true,
    },
  };

  return (
    <Chart
      options={{ ...options, labels: chartData.labels }}
      series={chartData.series}
      type="donut"
      height="240"
    />
  );
}

export default DonutChart;
